package com.mabcci.domain.ootd.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mabcci.domain.hashtag.domain.Hashtag;
import com.mabcci.domain.ootd.domain.Ootd;
import com.mabcci.domain.ootdhashtag.domain.OotdHashtag;
import com.mabcci.global.common.Nickname;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

import static java.util.stream.Collectors.toList;

public final class OotdResponse {

    private final static int FIRST = 0;

    @NotNull @Positive @JsonProperty("id")
    private Long id;

    @NotBlank @JsonProperty("nickname")
    private Nickname nickname;

    @NotBlank @JsonProperty("picture")
    private String picture;

    @NotNull @JsonProperty("hashtags")
    private List<String> hashtags;

    @PositiveOrZero @JsonProperty("likeCount")
    private Long likeCount;

    private OotdResponse() {
    }

    public final static OotdResponse ofOotd(final Ootd ootd) {
        return new OotdResponse(ootd.id(), ootd.member().nickname(),
                getRepresentativeOotdPicturePath(ootd), mapOotdHashtagsToHashtagNames(ootd), ootd.likeCount());
    }

    public OotdResponse(final Long id, final Nickname nickname, final String picture,
                        final List<String> hashtags, final Long likeCount) {
        this.id = id;
        this.nickname = nickname;
        this.picture = picture;
        this.hashtags = hashtags;
        this.likeCount = likeCount;
    }

    private final static String getRepresentativeOotdPicturePath(final Ootd ootd) {
        return ootd.ootdPictures()
                .get(FIRST)
                .path();
    }

    private final static List<String> mapOotdHashtagsToHashtagNames(final Ootd ootd) {
        return ootd.ootdHashtags()
                .stream()
                .map(OotdHashtag::hashtag)
                .map(Hashtag::name)
                .collect(toList());
    }

    public final Long id() {
        return id;
    }

    public final Nickname nickname() {
        return nickname;
    }

    public final String picture() {
        return picture;
    }

    public final List<String> hashtags() {
        return hashtags;
    }

    public final Long likeCount() {
        return likeCount;
    }
}
