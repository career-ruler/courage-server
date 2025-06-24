package org.example.courage.domain.profile.repository;

import org.example.courage.domain.profile.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, String> {

    Profile findByUserId(String userId);
}
