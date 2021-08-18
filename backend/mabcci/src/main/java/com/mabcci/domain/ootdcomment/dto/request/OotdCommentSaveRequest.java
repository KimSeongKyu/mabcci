package com.mabcci.domain.ootdcomment.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mabcci.global.common.Nickname;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

public final class OotdCommentSaveRequest {

    @NotNull @Positive @JsonProperty("ootdId")
    private Long ootdId;

    @Valid @NotNull @JsonProperty("nickname")
    private Nickname nickname;

    @PositiveOrZero @JsonProperty("parentCommentId")
    private Long parentCommentId;

    @NotEmpty @JsonProperty("content")
    private String content;

    private OotdCommentSaveRequest() {
    }

    public OotdCommentSaveRequest(final Long ootdId, final Nickname nickname, final Long parentCommentId, final String content) {
        this.ootdId = ootdId;
        this.nickname = nickname;
        this.parentCommentId = parentCommentId;
        this.content = content;
    }

    public final Long ootdId() {
        return ootdId;
    }

    public final Nickname nickname() {
        return nickname;
    }

    public final Long parentCommentId() {
        return parentCommentId;
    }

    public final String content() {
        return content;
    }
}
