package com.mabcci.domain.ootdcomment.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mabcci.domain.ootdcomment.domain.OotdComment;
import com.mabcci.global.common.Nickname;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.time.LocalDateTime;

public final class OotdCommentFindResponse {

    @NotBlank @JsonProperty("memberPicture")
    private String memberPicture;

    @Valid @NotNull @JsonProperty("memberNickname")
    private Nickname memberNickname;

    @NotNull @PastOrPresent @JsonProperty("createdDate")
    private LocalDateTime createdDate;

    @NotNull @PastOrPresent @JsonProperty("modifiedDate")
    private LocalDateTime modifiedDate;

    @NotBlank @JsonProperty("content")
    private String content;

    @Positive @JsonProperty("id")
    private Long id;

    @PositiveOrZero @JsonProperty("parentId")
    private Long parentId;

    private OotdCommentFindResponse() {
    }

    public static OotdCommentFindResponse ofOotdComment(final OotdComment ootdComment) {
        return new OotdCommentFindResponse(ootdComment.member().picture(), ootdComment.member().nickname(),
                ootdComment.createdDate(), ootdComment.modifiedDate(), ootdComment.content(),
                ootdComment.id(), ootdComment.parentComment().isPresent() ? ootdComment.parentComment().get().id() : 0L);
    }

    public OotdCommentFindResponse(final String memberPicture, final Nickname memberNickname,
                                   final LocalDateTime createdDate, final LocalDateTime modifiedDate,
                                   final String content, final Long id, final Long parentId) {
        this.memberPicture = memberPicture;
        this.memberNickname = memberNickname;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.content = content;
        this.id = id;
        this.parentId = parentId;
    }

    public final String memberPicture() {
        return memberPicture;
    }

    public final Nickname memberNickname() {
        return memberNickname;
    }

    public final LocalDateTime createdDate() {
        return createdDate;
    }

    public final LocalDateTime modifiedDate() {
        return modifiedDate;
    }

    public final String content() {
        return content;
    }

    public final Long id() {
        return id;
    }

    public final Long parentId() {
        return parentId;
    }
}
