package com.mabcci.domain.chat.dto;

import com.mabcci.domain.member.domain.Member;

public class ChattingParticipant {

    private String name;
    private String picture;

    public ChattingParticipant() {
    }

    public ChattingParticipant(final Member member) {
        this(member.nickname().nickname(), member.picture());
    }

    public ChattingParticipant(final String name, final String picture) {
        this.name = name;
        this.picture = picture;
    }

    public String getName() {
        return name;
    }

    public String getPicture() {
        return picture;
    }
}
