package com.mabcci.domain.ootd.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

public final class OotdResponse {

    @NotNull
    @Positive
    private Long id;

    @NotBlank
    private String nickname;

    @NotBlank
    private String picture;

    @NotNull
    private List<String> hashtags;

    @NotNull
    @PositiveOrZero
    private Long likeCount;

    private OotdResponse() {
    }

    public OotdResponse(final Long id, final String nickname, final String picture,
                        final List<String> hashtags, final Long likeCount) {
        this.id = id;
        this.nickname = nickname;
        this.picture = picture;
        this.hashtags = hashtags;
        this.likeCount = likeCount;
    }

    public final Long getId() {
        return id;
    }

    public final String getNickname() {
        return nickname;
    }

    public final String getPicture() {
        return picture;
    }

    public final List<String> getHashtags() {
        return hashtags;
    }

    public final Long getLikeCount() {
        return likeCount;
    }
}
