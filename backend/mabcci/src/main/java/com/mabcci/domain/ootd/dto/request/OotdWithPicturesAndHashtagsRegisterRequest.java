package com.mabcci.domain.ootd.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mabcci.global.common.Nickname;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

public final class OotdWithPicturesAndHashtagsRegisterRequest {

    @Valid @JsonProperty("nickname")
    private Nickname nickname;

    @JsonProperty("content")
    private String content;

    @JsonProperty("top")
    private String top;

    @JsonProperty("bottom")
    private String bottom;

    @JsonProperty("shoes")
    private String shoes;

    @JsonProperty("accessory")
    private String accessory;

    @NotEmpty @JsonProperty("pictures")
    private List<MultipartFile> pictures;

    @JsonProperty("hashtags")
    private List<String> hashtags;


    public OotdWithPicturesAndHashtagsRegisterRequest(final Nickname nickname, final String content, final String top,
                                                      final String bottom, final String shoes, final String accessory,
                                                      final List<MultipartFile> pictures, final List<String> hashtags) {
        this.nickname = nickname;
        this.content = content;
        this.top = top;
        this.bottom = bottom;
        this.shoes = shoes;
        this.accessory = accessory;
        this.pictures = pictures;
        this.hashtags = hashtags;
    }

    public final Nickname nickname() {
        return nickname;
    }

    public final String content() {
        return content;
    }

    public final String top() {
        return top;
    }

    public final String bottom() {
        return bottom;
    }

    public final String shoes() {
        return shoes;
    }

    public final String accessory() {
        return accessory;
    }

    public final List<MultipartFile> pictures() {
        return pictures;
    }

    public final List<String> hashtags() {
        return hashtags;
    }
}
