package com.example.webflux.uniconn.user.domain;

public enum Major {
    COMPUTER("컴퓨터"),
    BUSINESS("경영"),
    ENGINEERING("공학");

    private final String major;

    Major(String major) {
        this.major = major;
    }
}
