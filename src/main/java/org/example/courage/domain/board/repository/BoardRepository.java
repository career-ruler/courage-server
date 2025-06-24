package org.example.courage.domain.board.repository;

import org.example.courage.domain.board.entity.Board;
import org.example.courage.domain.board.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Integer> {

    @Query("SELECT p FROM Board p WHERE p.userId.userId = :userId ORDER BY p.createdDate DESC")
    List<Board> findByUserIdOrderByCreatedDateDesc(@Param("userId") String userId);

    Board findByBoardId(int id);

    List<Board> findByCategory(Category category);
}
