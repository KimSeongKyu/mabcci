package com.mabcci.domain.ootd.dto;

import com.mabcci.domain.member.domain.Member;
import com.mabcci.domain.ootd.domain.Ootd;

import javax.validation.constraints.NotNull;

public final class OotdSaveRequest {

    @NotNull
    private Member member;

    private String content;
    private String top;
    private String bottom;
    private String shoes;
    private String accessory;

    private OotdSaveRequest() {
    }

    public OotdSaveRequest(final Member member, final String content, final String top,
                           final String bottom, final String shoes, final String accessory) {
        this.member = member;
        this.content = content;
        this.top = top;
        this.bottom = bottom;
        this.shoes = shoes;
        this.accessory = accessory;
    }

    public final Member getMember() {
        return member;
    }

    public final String getContent() {
        return content;
    }

    public final String getTop() {
        return top;
    }

    public final String getBottom() {
        return bottom;
    }

    public final String getShoes() {
        return shoes;
    }

    public final String getAccessory() {
        return accessory;
    }

    public final Ootd ootd() {
        return Ootd.builder()
                .member(member)
                .content(content)
                .top(top)
                .bottom(bottom)
                .shoes(shoes)
                .accessory(accessory)
                .build();
    }
}
