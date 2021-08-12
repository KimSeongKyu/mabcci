package com.mabcci.domain.ootdcomment.dto.response;

import com.mabcci.global.common.Nickname;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDateTime;

public final class OotdCommentResponse {

    @NotBlank
    private String memberPicture;

    @Valid @NotNull
    private Nickname memberNickname;

    @NotNull @PastOrPresent
    private LocalDateTime createdDate;

    @NotNull @PastOrPresent
    private LocalDateTime modifiedDate;

    @NotBlank
    private String content;

    private OotdCommentResponse() {
    }

    public OotdCommentResponse(final String memberPicture, final Nickname memberNickname,
                               final LocalDateTime createdDate, final LocalDateTime modifiedDate, final String content) {
        this.memberPicture = memberPicture;
        this.memberNickname = memberNickname;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.content = content;
    }

    public final String getMemberPicture() {
        return memberPicture;
    }

    public final Nickname getMemberNickname() {
        return memberNickname;
    }

    public final LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public final LocalDateTime getModifiedDate() {
        return modifiedDate;
    }

    public final String getContent() {
        return content;
    }
}
