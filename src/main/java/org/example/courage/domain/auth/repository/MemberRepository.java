package org.example.courage.domain.auth.repository;


import org.example.courage.domain.auth.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, String> {
    boolean existsByUserId(String userId);
    Optional<MemberEntity> findByUserId(String userId);

}
