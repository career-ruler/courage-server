package org.example.courage.domain.board.controller;

import lombok.RequiredArgsConstructor;
import org.example.courage.domain.auth.entity.MemberEntity;
import org.example.courage.domain.board.dto.BoardRequest;
import org.example.courage.domain.board.dto.GetBoardResponse;
import org.example.courage.domain.board.dto.BoardResponse;
import org.example.courage.domain.board.entity.Category;
import org.example.courage.domain.board.service.BoardQueryService;
import org.example.courage.domain.board.service.BoardService;
import org.example.courage.global.auth.GetAuthenticatedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private BoardService boardService;

    @Autowired
    private BoardQueryService boardQueryService;

    // 게시물 생성 (POST /board)
    @PostMapping
    public ResponseEntity<BoardResponse> createProduct(
            @GetAuthenticatedUser MemberEntity member,
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("category") Category category,
            @RequestParam("picture") MultipartFile picture) {
        BoardResponse response = boardService.createProduct(member, title, description, category, picture);
        return ResponseEntity.status(201).body(response);
    }



    // 게시물 삭제 (DELETE /board/{id})
    @DeleteMapping("/{id}")
    public ResponseEntity<Integer> deleteProduct(
            @GetAuthenticatedUser MemberEntity member,
            @PathVariable int id) {
        int deleteId = boardService.deleteProduct(member, id);
        return ResponseEntity.ok(deleteId);
    }



    // 게시물 상세 조회 (GET /board/{id})
    @GetMapping("/{id}")
    public ResponseEntity<BoardResponse> getProduct(
            @GetAuthenticatedUser MemberEntity member,
            @PathVariable int id) {
        BoardResponse response = boardQueryService.getProduct(member, id);
        return ResponseEntity.ok(response);
    }

    // 게시물 전체 조회(홈) (GET /board)
    @GetMapping
    public ResponseEntity<List<GetBoardResponse>> getAllProduct(
            @GetAuthenticatedUser MemberEntity member) {
        List<GetBoardResponse> responses = boardQueryService.getAllProduct(member);
        return ResponseEntity.ok(responses);
    }

    // 게시물 전체 조회(태그) (GET /board/category)
    @GetMapping("/category")
    public ResponseEntity<List<GetBoardResponse>> getCategoryProduct(
            @GetAuthenticatedUser MemberEntity member,
            @RequestParam Category category) {
        List<GetBoardResponse> responses = boardQueryService.getCategoryProduct(member, category);
        return ResponseEntity.ok(responses);
    }

    // 게시물 수정 (PATCH /board/{id})
    @PatchMapping("/{id}")
    public ResponseEntity<BoardResponse> editProduct(
            @GetAuthenticatedUser MemberEntity member,
            @PathVariable int id,
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("category") Category category,
            @RequestParam(value = "picture", required = false) MultipartFile picture) {
        BoardResponse response = boardService.editProduct(member, id, title, description, category, picture);
        return ResponseEntity.ok(response);
    }
}
