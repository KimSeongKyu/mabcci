package com.mabcci.member.domain;

public enum MemberRole {
    USER("일반 사용자"),
    MABCCI("맵시"),
    ADMIN("관리자");

    private final String role;

    MemberRole(final String role) {
        this.role = role;
    }

    public String role() {
        return role;
    }
}
