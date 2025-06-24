package org.example.courage.domain.comment.repository;

import org.example.courage.domain.board.entity.Board;
import org.example.courage.domain.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
    Comment findByCommentId(int id);

    List<Comment> findByBoard(Board board);
}
