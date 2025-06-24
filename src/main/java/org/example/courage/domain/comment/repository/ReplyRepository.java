package org.example.courage.domain.comment.repository;

import org.example.courage.domain.comment.entity.Comment;
import org.example.courage.domain.comment.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply, Integer> {
    Reply findByReplyId(int id);

}