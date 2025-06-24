package org.example.courage.global.auth;

import lombok.RequiredArgsConstructor;
import org.example.courage.domain.auth.entity.MemberEntity;
import org.example.courage.domain.auth.repository.MemberRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {

    private final MemberRepository repository;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        return loadByUserId(userId);
    }

    public UserDetails loadByUserId(String userId) {

        MemberEntity entity = repository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("유저가 존재하지 않습니다"));

        return new JwtUserDetails(entity);
    }
}
