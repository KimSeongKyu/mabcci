package com.mabcci.domain.ootd.dto.request;

import javax.validation.constraints.NotNull;

public final class OotdSaveRequest {

    @NotNull
    private String nickname;

    private String content;
    private String top;
    private String bottom;
    private String shoes;
    private String accessory;

    private OotdSaveRequest() {
    }

    public OotdSaveRequest(final String nickname, final String content, final String top,
                           final String bottom, final String shoes, final String accessory) {
        this.nickname = nickname;
        this.content = content;
        this.top = top;
        this.bottom = bottom;
        this.shoes = shoes;
        this.accessory = accessory;
    }

    public final String getNickname() {
        return nickname;
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
}
