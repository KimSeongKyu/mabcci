package com.mabcci.domain.ootdcomment.dto.request;

import com.mabcci.global.common.Nickname;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public final class OotdCommentUpdateRequest {

    @Valid
    @NotNull
    private Nickname nickname;

    @NotEmpty
    private String content;

    private OotdCommentUpdateRequest() {
    }

    public OotdCommentUpdateRequest(final Nickname nickname, final String content) {
        this.nickname = nickname;
        this.content = content;
    }

    public final Nickname getNickname() {
        return nickname;
    }

    public final String getContent() {
        return content;
    }
}
