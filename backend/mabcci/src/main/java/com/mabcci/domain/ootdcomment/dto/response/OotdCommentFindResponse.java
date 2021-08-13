package com.mabcci.domain.ootdcomment.dto.response;

import com.mabcci.domain.ootdcomment.domain.OotdComment;
import com.mabcci.global.common.Nickname;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.time.LocalDateTime;

public final class OotdCommentFindResponse {

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

    @Positive
    private Long id;

    @PositiveOrZero
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

    public final Long getId() {
        return id;
    }

    public final Long getParentId() {
        return parentId;
    }
}
