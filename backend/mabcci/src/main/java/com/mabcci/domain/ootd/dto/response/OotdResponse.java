package com.mabcci.domain.ootd.dto.response;

import com.mabcci.domain.hashtag.domain.Hashtag;
import com.mabcci.domain.ootd.domain.Ootd;
import com.mabcci.domain.ootdLike.domain.OotdLike;
import com.mabcci.domain.ootdhashtag.domain.OotdHashtag;
import com.mabcci.global.common.Nickname;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

import static java.util.stream.Collectors.toList;

public final class OotdResponse {

    private final static int FIRST = 0;

    @NotNull
    @Positive
    private Long id;

    @NotBlank
    private Nickname nickname;

    @NotBlank
    private String picture;

    @NotNull
    private List<String> hashtags;

    @PositiveOrZero
    private Long likeCount;

    private OotdResponse() {
    }

    public OotdResponse(@Valid final Ootd ootd) {
        this.id = ootd.id();
        this.nickname = ootd.member()
                .nickname();
        this.picture = ootd.ootdPictures()
                .get(FIRST)
                .path();
        this.hashtags = ootd.ootdHashtags()
                .stream()
                .map(OotdHashtag::hashtag)
                .map(Hashtag::name)
                .collect(toList());
        this.likeCount = ootd.ootdLikes()
                .stream()
                .filter(OotdLike::status)
                .count();
    }

    public final Long getId() {
        return id;
    }

    public final Nickname getNickname() {
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
