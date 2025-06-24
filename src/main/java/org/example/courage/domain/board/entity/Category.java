package org.example.courage.domain.board.entity;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Category {

    WEB("웹"),
    SERVER("서버"),
    iOS("iOS"),
    ANDROID("안드로이드"),
    AI("인공지능");
//    OTHER("기타");

    private final String categoryName;

//    public String getCategoryName() {
//        return categoryName;
//    }
}
