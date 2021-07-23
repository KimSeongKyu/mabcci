package com.mabcci.member.domain;

public enum Gender {
    MALE("남"),
    FEMALE("여");

    final String gender;

    Gender(final String gender) {
        this.gender = gender;
    }

    public String gender() {
        return this.gender;
    }

}
