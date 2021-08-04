package com.mabcci.domain.picture.dto;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;
import java.util.List;

public final class PictureRegisterRequest {

    @NotEmpty
    private List<MultipartFile> pictures;

    public PictureRegisterRequest(final List<MultipartFile> pictures) {
        this.pictures = pictures;
    }
}
