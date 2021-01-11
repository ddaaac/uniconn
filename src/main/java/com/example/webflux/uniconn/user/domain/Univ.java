package com.example.webflux.uniconn.user.domain;

public enum Univ {
    SEOUL_NATIONAL("서울대학교"),
    KOREA("고려대학교"),
    YONSEI("연세대학교"),
    SEOGANG("서강대학교"),
    SUNGKYUNKWAN("성균관대학교"),
    HANYANG("한양대학교");

    private final String univ;

    Univ(String univ) {
        this.univ = univ;
    }
}
