package org.example.courage.domain.comment.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.courage.domain.auth.entity.MemberEntity;
import org.example.courage.domain.board.dto.BoardResponse;
import org.example.courage.domain.board.entity.Board;
import org.example.courage.domain.board.repository.BoardRepository;
import org.example.courage.domain.comment.dto.CommentRequest;
import org.example.courage.domain.comment.dto.CommentResponse;
import org.example.courage.domain.comment.entity.Comment;
import org.example.courage.domain.comment.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CommentService {


    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;

    @Transactional
    public CommentResponse createComment(MemberEntity member, int id, CommentRequest request) {

        Board boardId = boardRepository.findByBoardId(id);

        Comment comment = Comment.builder()
                .board(boardId)
                .userId(member)
                .content(request.getContent())
                .createdDate(LocalDateTime.now())
                .build();

        comment = commentRepository.save(comment);
        return CommentResponse.from(comment);
    }

    @Transactional
    public int deleteComment(MemberEntity member, int id) {

        Comment comment = commentRepository.findByCommentId(id);

        if(!comment.getUserId().getUserId().equals(member.getUserId())) {
            throw new RuntimeException("no permission to delete this comment");
        }

        commentRepository.delete(comment);
        return id;
    }
}



