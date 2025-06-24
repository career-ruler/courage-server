package org.example.courage.domain.board.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.courage.domain.auth.entity.MemberEntity;
import org.example.courage.domain.board.dto.BoardRequest;
import org.example.courage.domain.board.dto.GetBoardResponse;
import org.example.courage.domain.board.dto.BoardResponse;
import org.example.courage.domain.board.entity.Category;
import org.example.courage.domain.board.entity.Board;
import org.example.courage.domain.board.repository.BoardRepository;
import org.example.courage.domain.comment.entity.Comment;
import org.example.courage.domain.comment.entity.Reply;
import org.example.courage.domain.comment.repository.CommentRepository;
import org.example.courage.domain.comment.repository.ReplyRepository;
import org.example.courage.domain.s3.controller.S3Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardQueryService {

    private final BoardRepository productRepository;
    private final S3Controller s3Controller;
    private final CommentRepository commentRepository;
    private final ReplyRepository replyRepository;
    private final BoardRepository boardRepository;


    // 상품 상세 조회
    @Transactional
    public BoardResponse getProduct(MemberEntity member, int id) {
        Board board = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        List<Comment> comments = commentRepository.findByBoard(board);
//        List<Reply> replies = replyRepository.findByComment_CommentIdIn(comments);

//        List<Integer> commentIds = comments.stream()
//                .map(Comment::getCommentId)
//                .toList();

//        List<Reply> replies = replyRepository.findByComment_CommentIdIn(commentIds);


        if (!board.getUserId().getUserId().equals(member.getUserId())) {
            throw new RuntimeException("no permission to view this product");
        }

        return BoardResponse.builder()
                .boardId(board.getBoardId())
                .userId(board.getUserId().getUserId())
                .title(board.getTitle())
                .description(board.getDescription())
                .picture(Optional.ofNullable(board.getPicture()))
                .category(board.getCategory())
                .createdDate(board.getCreatedDate())
//                .comments(comments)
                .build();

//        return BoardResponse.getDetail(board, comments);
    }

    // 상품 전체 조회
    @Transactional
    public List<GetBoardResponse> getAllProduct(MemberEntity member) {
        return productRepository.findAll()
                .stream()
                .map(GetBoardResponse::from)
                .collect(Collectors.toList());
    }

    @Transactional
    public List<GetBoardResponse> getCategoryProduct(MemberEntity member, Category category) {

        List<Board> boards = boardRepository.findByCategory(category);

        return boards.stream()
                .map(GetBoardResponse::from)
                .collect(Collectors.toList());
    }

}