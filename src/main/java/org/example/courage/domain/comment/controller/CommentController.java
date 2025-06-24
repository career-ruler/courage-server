package org.example.courage.domain.comment.controller;

import lombok.RequiredArgsConstructor;
import org.example.courage.domain.auth.entity.MemberEntity;
import org.example.courage.domain.comment.dto.CommentRequest;
import org.example.courage.domain.comment.dto.CommentResponse;
import org.example.courage.domain.comment.entity.Comment;
import org.example.courage.domain.comment.service.CommentService;
import org.example.courage.global.auth.GetAuthenticatedUser;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/board/{id}/comment")
    public ResponseEntity<CommentResponse> createComment(
            @GetAuthenticatedUser MemberEntity member,
            @PathVariable int id,
            @RequestBody CommentRequest request) {

        CommentResponse response = commentService.createComment(member, id, request);

        return ResponseEntity.status(201).body(response);
    }

    @DeleteMapping("/comment/{id}")
    public ResponseEntity<Integer> deleteComment(
            @GetAuthenticatedUser MemberEntity member,
            @PathVariable int id) {

        int deletedId = commentService.deleteComment(member, id);

        return ResponseEntity.ok(deletedId);
    }


}
