package com.mabcci.domain.ootd.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mabcci.domain.hashtag.domain.Hashtag;
import com.mabcci.domain.ootd.domain.Ootd;
import com.mabcci.domain.ootdLike.domain.OotdLike;
import com.mabcci.domain.ootdhashtag.domain.OotdHashtag;
import com.mabcci.domain.picture.domain.Picture;
import com.mabcci.global.common.Nickname;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.List;

import static java.util.stream.Collectors.toList;

public final class OotdDetailResponse {

    @NotBlank @JsonProperty("memberPicture")
    private String memberPicture;

    @Valid @NotNull @JsonProperty("nickname")
    private Nickname nickname;

    @PastOrPresent @NotNull @JsonProperty("createdDate")
    private LocalDateTime createdDate;

    @PastOrPresent @NotNull @JsonProperty("modifiedDate")
    private LocalDateTime modifiedDate;

    @PositiveOrZero @NotNull @JsonProperty("views")
    private Long views;

    @NotEmpty @JsonProperty("ootdPictures")
    private List<String> ootdPictures;

    @PositiveOrZero @NotNull @JsonProperty("likeCount")
    private Long likeCount;

    @NotBlank @JsonProperty("content")
    private String content;

    @JsonProperty("top")
    private String top;

    @JsonProperty("bottom")
    private String bottom;

    @JsonProperty("shoes")
    private String shoes;

    @JsonProperty("accessory")
    private String accessory;

    @NotNull @JsonProperty("hashtags")
    private List<String> hashtags;

    public final static OotdDetailResponse ofOotd(final Ootd ootd) {
        return new OotdDetailResponse(ootd.member().picture(), ootd.member().nickname(), ootd.createdDate(), ootd.modifiedDate(),
                ootd.views(), mapOotdToOotdPicturePaths(ootd), countLikes(ootd), ootd.content(), ootd.top(), ootd.bottom(), ootd.shoes(),
                ootd.accessory(), mapOotdToOotdHashtagNames(ootd));
    }

    public OotdDetailResponse(final String memberPicture, final Nickname nickname, final LocalDateTime createdDate,
                              final LocalDateTime modifiedDate, final Long views, final List<String> ootdPictures,
                              final Long likeCount, final String content, final String top, final String bottom,
                              final String shoes, final String accessory, final List<String> hashtags) {
        this.memberPicture = memberPicture;
        this.nickname = nickname;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.views = views;
        this.ootdPictures = ootdPictures;
        this.likeCount = likeCount;
        this.content = content;
        this.top = top;
        this.bottom = bottom;
        this.shoes = shoes;
        this.accessory = accessory;
        this.hashtags = hashtags;
    }

    private final static List<String> mapOotdToOotdPicturePaths(final Ootd ootd) {
        return ootd.ootdPictures()
                .stream()
                .map(Picture::path)
                .collect(toList());
    }

    private final static Long countLikes(final Ootd ootd) {
        return ootd.ootdLikes()
                .stream()
                .filter(OotdLike::status)
                .count();
    }

    private final static List<String> mapOotdToOotdHashtagNames(final Ootd ootd) {
        return ootd.ootdHashtags()
                .stream()
                .map(OotdHashtag::hashtag)
                .map(Hashtag::name)
                .collect(toList());
    }
}
