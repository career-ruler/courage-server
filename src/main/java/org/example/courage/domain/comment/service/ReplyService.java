package org.example.courage.domain.comment.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.courage.domain.auth.entity.MemberEntity;
import org.example.courage.domain.board.entity.Board;
import org.example.courage.domain.board.repository.BoardRepository;
import org.example.courage.domain.comment.dto.CommentRequest;
import org.example.courage.domain.comment.dto.CommentResponse;
import org.example.courage.domain.comment.dto.ReplyRequest;
import org.example.courage.domain.comment.dto.ReplyResponse;
import org.example.courage.domain.comment.entity.Comment;
import org.example.courage.domain.comment.entity.Reply;
import org.example.courage.domain.comment.repository.CommentRepository;
import org.example.courage.domain.comment.repository.ReplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ReplyService {

    private final ReplyRepository replyRepository;
    private final CommentRepository commentRepository;

    @Transactional
    public ReplyResponse createReply(MemberEntity member, int id, ReplyRequest request) {

        Comment commentId = commentRepository.findByCommentId(id);


        Reply reply = Reply.builder()
                .comment(commentId)
                .userId(member)
                .content(request.getContent())
                .createdDate(LocalDateTime.now())
                .build();

        reply = replyRepository.save(reply);
        return ReplyResponse.from(reply);
    }

    @Transactional
    public int deleteReply(MemberEntity member, int id) {

        Reply reply = replyRepository.findByReplyId(id);

        if(!reply.getUserId().getUserId().equals(member.getUserId())) {
            throw new RuntimeException("no permission to delete this product");
        }

        replyRepository.delete(reply);
        return id;
    }
}



