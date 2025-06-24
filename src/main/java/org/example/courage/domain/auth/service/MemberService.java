package org.example.courage.domain.auth.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.courage.domain.auth.entity.MemberType;
import org.example.courage.global.exception.MemberException;
//import org.example.courage.domain.auth.MemberType;
import org.example.courage.domain.auth.dto.RefreshMemberRequest;
import org.example.courage.domain.auth.dto.SigninMemberRequest;
import org.example.courage.domain.auth.dto.SignupMemberRequest;
import org.example.courage.domain.auth.entity.MemberEntity;
import org.example.courage.domain.auth.repository.MemberRepository;
import org.example.courage.domain.profile.entity.Profile;
import org.example.courage.domain.profile.repository.ProfileRepository;
import org.example.courage.global.auth.JwtUtils;
import org.example.courage.global.auth.TokenInfo;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository repository;
    private final ProfileRepository profileRepository;
    private final JwtUtils utils;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    @Transactional
    public TokenInfo signupMember(SignupMemberRequest dto) {

        if(repository.existsByUserId(dto.userId())) {
            throw MemberException.ALREADY_EXIST.getException();
        }


        String encodedPassword = passwordEncoder.encode(dto.password());

        MemberEntity entity = MemberEntity.builder()
                .userId(dto.userId())
                .password(encodedPassword)
                .role(MemberType.ROLE_USER)
                .build();


        repository.save(entity);

        Profile profile = Profile.builder()
                .userId(dto.userId())
                .build();

        profileRepository.save(profile);

        return utils.generate(entity);
    }


    @Transactional
    public TokenInfo signinMember(SigninMemberRequest dto) {
        MemberEntity entity = repository.findByUserId(dto.userId())
                .orElseThrow(MemberException.NOT_EXIST::getException);

        if (!passwordEncoder.matches(dto.password(), entity.getPassword())) {
            System.out.println("입력된 비밀번호: " + dto.password());
            System.out.println("DB에 저장된 암호화된 비밀번호: " + entity.getPassword());
            System.out.println("비교 결과: " + passwordEncoder.matches(dto.password(), entity.getPassword()));

            throw MemberException.PASSWORD_NOT_MATCH.getException();
        }

        return utils.generate(entity);
    }


    @Transactional
    public String refreshMember(RefreshMemberRequest dto) {
        String userId = utils.parse(dto.refreshToken()).get("userId", String.class);

        MemberEntity entity = repository.findByUserId(userId)
                .orElseThrow(MemberException.NOT_EXIST::getException);

        return utils.refreshToken(entity);
    }

    public void deleteMember(SigninMemberRequest dto) {
        MemberEntity entity = repository.findByUserId(dto.userId())
                .orElseThrow(MemberException.NOT_EXIST::getException);
        repository.delete(entity);
    }
}
