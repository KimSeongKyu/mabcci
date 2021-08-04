package com.mabcci.domain.ootd.dto;

import javax.validation.constraints.NotBlank;

public final class OotdRegisterRequest {

    @NotBlank
    private String nickname;

    private String content;
    private String top;
    private String bottom;
    private String shoes;
    private String accessory;

    private OotdRegisterRequest() {
    }

    public OotdRegisterRequest(final String nickname, final String content, final String top,
                               final String bottom, final String shoes, final String accessory) {
        this.nickname = nickname;
        this.content = content;
        this.top = top;
        this.bottom = bottom;
        this.shoes = shoes;
        this.accessory = accessory;
    }
}
