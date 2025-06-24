package org.example.courage.domain.comment.controller;

import lombok.RequiredArgsConstructor;
import org.example.courage.domain.auth.entity.MemberEntity;
import org.example.courage.domain.comment.dto.CommentRequest;
import org.example.courage.domain.comment.dto.CommentResponse;
import org.example.courage.domain.comment.dto.ReplyRequest;
import org.example.courage.domain.comment.dto.ReplyResponse;
import org.example.courage.domain.comment.entity.Comment;
import org.example.courage.domain.comment.service.CommentService;
import org.example.courage.domain.comment.service.ReplyService;
import org.example.courage.global.auth.GetAuthenticatedUser;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ReplyController {

    private final ReplyService replyService;

    @PostMapping("/comment/{id}/reply")
    public ResponseEntity<ReplyResponse> createReply(
            @GetAuthenticatedUser MemberEntity member,
            @PathVariable int id,
            @RequestBody ReplyRequest request) {

        ReplyResponse response = replyService.createReply(member, id, request);

        return ResponseEntity.status(201).body(response);
    }

    @DeleteMapping("/reply/{id}")
    public ResponseEntity<Integer> deleteReply(
            @GetAuthenticatedUser MemberEntity member,
            @PathVariable int id) {

        int deletedId = replyService.deleteReply(member, id);

        return ResponseEntity.ok(deletedId);
    }


}
