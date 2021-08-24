package com.mabcci.domain.chat.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mabcci.domain.member.domain.Member;

public class Chatter {

    @JsonProperty("nickname")
    private String nickname;

    @JsonProperty("picture")
    private String picture;

    private Chatter() {
    }

    public Chatter(final Member member) {
        this(member.nickname().nickname(), member.picture());
    }

    private Chatter(final String nickname, final String picture) {
        this.nickname = nickname;
        this.picture = picture;
    }

    public String nickname() {
        return nickname;
    }

    public String picture() {
        return picture;
    }

}
