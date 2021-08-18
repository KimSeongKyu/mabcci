package com.mabcci.domain.ootd.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

public final class OotdSaveRequest {

    @NotNull @JsonProperty("nickname")
    private String nickname;

    @JsonProperty("content")
    private String content;

    @JsonProperty("top")
    private String top;

    @JsonProperty("bottom")
    private String bottom;

    @JsonProperty("shoes")
    private String shoes;

    @JsonProperty("accessory")
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

    public final String nickname() {
        return nickname;
    }

    public final String content() {
        return content;
    }

    public final String top() {
        return top;
    }

    public final String bottom() {
        return bottom;
    }

    public final String shoes() {
        return shoes;
    }

    public final String accessory() {
        return accessory;
    }
}
