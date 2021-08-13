package com.mabcci.domain.ootdcomment.dto.request;

import com.mabcci.global.common.Nickname;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

public final class OotdCommentSaveRequest {

    @NotNull
    @Positive
    private Long ootdId;

    @Valid
    @NotNull
    private Nickname nickname;

    @PositiveOrZero
    private Long parentCommentId;

    @NotEmpty
    private String content;

    private OotdCommentSaveRequest() {
    }

    public OotdCommentSaveRequest(final Long ootdId, final Nickname nickname, final Long parentCommentId, final String content) {
        this.ootdId = ootdId;
        this.nickname = nickname;
        this.parentCommentId = parentCommentId;
        this.content = content;
    }

    public final Long getOotdId() {
        return ootdId;
    }

    public final Nickname getNickname() {
        return nickname;
    }

    public final Long getParentCommentId() {
        return parentCommentId;
    }

    public final String getContent() {
        return content;
    }
}
