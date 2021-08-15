package com.mabcci.domain.ootdcomment.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mabcci.global.common.Nickname;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public final class OotdCommentUpdateRequest {

    @Valid @NotNull @JsonProperty("nickname")
    private Nickname nickname;

    @NotEmpty @JsonProperty("content")
    private String content;

    private OotdCommentUpdateRequest() {
    }

    public OotdCommentUpdateRequest(final Nickname nickname, final String content) {
        this.nickname = nickname;
        this.content = content;
    }

    public final Nickname nickname() {
        return nickname;
    }

    public final String content() {
        return content;
    }
}
