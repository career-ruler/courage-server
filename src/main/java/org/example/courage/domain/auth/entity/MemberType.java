package org.example.courage.domain.auth.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MemberType {

    ROLE_USER("user");
//    ROLE_ADMIN("admin");

    private final String value;
}
