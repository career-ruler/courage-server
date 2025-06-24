package org.example.courage.domain.s3.repository;


import org.example.courage.domain.s3.entity.ImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface S3JpaRepository extends JpaRepository<ImageEntity, Long> {
}