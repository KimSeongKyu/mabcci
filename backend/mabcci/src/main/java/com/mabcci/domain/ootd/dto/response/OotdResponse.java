package com.mabcci.domain.ootd.dto.response;

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

    private OotdResponse() {
    }

    public OotdResponse(final Long id, final String nickname, final String picture,
                        final List<String> hashtags) {
        this.id = id;
        this.nickname = nickname;
        this.picture = picture;
        this.hashtags = hashtags;
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
}
