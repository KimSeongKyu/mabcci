package com.mabcci.domain.ootd.dto;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

public final class OotdRegisterRequest {

    @NotBlank
    private String nickname;

    private String content;
    private String top;
    private String bottom;
    private String shoes;
    private String accessory;

    @NotEmpty
    private List<MultipartFile> pictures;

    private List<String> hashtagNames;


    public OotdRegisterRequest(final String nickname, final String content, final String top, final String bottom, final String shoes,
                               final String accessory, final List<MultipartFile> pictures, final List<String> hashtagNames) {
        this.nickname = nickname;
        this.content = content;
        this.top = top;
        this.bottom = bottom;
        this.shoes = shoes;
        this.accessory = accessory;
        this.pictures = pictures;
        this.hashtagNames = hashtagNames;
    }
}
