package org.example.courage.domain.board.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.courage.domain.auth.entity.MemberEntity;
import org.example.courage.domain.board.dto.GetBoardResponse;
import org.example.courage.domain.board.dto.BoardResponse;
import org.example.courage.domain.board.entity.Category;
import org.example.courage.domain.board.entity.Board;
import org.example.courage.domain.board.repository.BoardRepository;
import org.example.courage.domain.s3.controller.S3Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private S3Controller s3Controller;

    @Transactional
    public BoardResponse createProduct(MemberEntity member, String title, String description, Category category, MultipartFile picture) {
        String pictureUrl = null;

        if (picture != null && !picture.isEmpty()) {
            try {
                pictureUrl = s3Controller.s3Upload(picture);
            } catch (Exception e) {
                throw new RuntimeException("파일 업로드 중 오류가 발생했습니다. (사진파일이 null이거나 비어있습니다.)", e);
            }
        }

        Board board = Board.builder()
                .userId(member)
                .title(title)
                .description(description)
                .picture(pictureUrl)
                .category(category)
                .createdDate(LocalDateTime.now())
                .build();

        board = boardRepository.save(board);
        return BoardResponse.from(board);
    }



    // 상품 삭제
    @Transactional
    public int deleteProduct(MemberEntity member, int id){
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        if (!board.getUserId().getUserId().equals(member.getUserId())) {
            throw new RuntimeException("no permission to delete this product");
        }

        s3Controller.s3Delete(board.getPicture());
        boardRepository.delete(board);
        return id;
    }


    @Transactional
    public BoardResponse editProduct(MemberEntity member, int id, String title, String description, Category category, MultipartFile picture) {
        Board product = boardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        if (!product.getUserId().getUserId().equals(member.getUserId())) {
            throw new RuntimeException("No permission to edit this product");
        }

        product.setTitle(title);
        product.setDescription(description);
        product.setCategory(category);

        if (picture != null && !picture.isEmpty()) {
            s3Controller.s3Delete(product.getPicture());
            product.setPicture(s3Controller.s3Upload(picture));
        }

        return BoardResponse.from(boardRepository.save(product));
    }

}