package com.mabcci.domain.ootd.dto.request;

import com.mabcci.global.common.Nickname;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

public final class OotdWithPicturesAndHashtagsRegisterRequest {

    @Valid
    private Nickname nickname;

    private String content;
    private String top;
    private String bottom;
    private String shoes;
    private String accessory;

    @NotEmpty
    private List<MultipartFile> pictures;

    private List<String> hashtags;


    public OotdWithPicturesAndHashtagsRegisterRequest(@Valid final Nickname nickname, final String content, final String top,
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

    public final Nickname getNickname() {
        return nickname;
    }

    public final String getContent() {
        return content;
    }

    public final String getTop() {
        return top;
    }

    public final String getBottom() {
        return bottom;
    }

    public final String getShoes() {
        return shoes;
    }

    public final String getAccessory() {
        return accessory;
    }

    public final List<MultipartFile> getPictures() {
        return pictures;
    }

    public final List<String> getHashtags() {
        return hashtags;
    }
}
