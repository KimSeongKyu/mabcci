package com.mabcci.domain.ootdcomment.dto.request;

import com.mabcci.global.common.Nickname;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public final class OotdCommentSaveRequest {

    @NotNull
    @Positive
    private Long ootdId;

    @Valid
    @NotNull
    private Nickname nickname;

    @Positive
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
}
