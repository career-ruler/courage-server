package org.example.courage.global.auth;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.example.courage.domain.auth.entity.MemberEntity;
import org.example.courage.global.exception.GlobalException;
import org.example.courage.global.auth.JwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;


@Component
@RequiredArgsConstructor
public class JwtUtils {

    private final JwtProperties properties;
    private SecretKey secretKey;
    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);


    @PostConstruct
    public void init() {
        String secretKey = properties.getSecretKey();
        System.out.println("JWT Secret Key: " + secretKey);

        byte[] keyBytes = Base64.getDecoder().decode(secretKey);
        this.secretKey = Keys.hmacShaKeyFor(keyBytes);
    }


    private String createToken(MemberEntity member, Long expiration) {
        long now = System.currentTimeMillis();

        if (member.getUserId() == null) {
            throw new IllegalArgumentException("userId cannot be null");
        }

        return Jwts.builder()
                .claim("userId", member.getUserId())
                .issuedAt(new Date())
                .expiration(new Date(now + expiration))
                .signWith(secretKey, Jwts.SIG.HS256)
                .compact();
    }


    public TokenInfo generate(MemberEntity entity) {
        return new TokenInfo(
                createToken(entity, properties.getAccessExpire()),
                createToken(entity, properties.getRefreshExpire())
        );
    }

    public String refreshToken(MemberEntity entity) {
        return createToken(entity, properties.getAccessExpire());
    }

    public Claims parse(String token) {

        String cleanToken = token.replace("Bearer ", "");

        try {
            return Jwts.parser()
                    .verifyWith(secretKey)
                    .build()
                    .parseSignedClaims(cleanToken)
                    .getPayload();
        } catch (ExpiredJwtException e) {
            logger.error("Expired JWT: {}", e.getMessage());
            throw JwtException.EXPIRED.getException();
        } catch (SignatureException e) {
            logger.error("Invalid JWT Signature: {}", e.getMessage());
            throw JwtException.SIGNATURE.getException();
        } catch (MalformedJwtException e) {
            logger.error("Malformed JWT: {}", e.getMessage());
            throw JwtException.MALFORMED.getException();
        } catch (UnsupportedJwtException e) {
            logger.error("Unsupported JWT: {}", e.getMessage());
            throw JwtException.UNSUPPORTED.getException();
        } catch (IllegalArgumentException e) {
            logger.error("JWT Illegal Argument: {}", e.getMessage());
            throw JwtException.ILLEGAL_ARGUMENT.getException();
        } catch (Exception e) {
            logger.error("Unexpected JWT error: {}", e.getMessage());
            throw GlobalException.SERVER_ERROR.getException();
        }

    }
}
