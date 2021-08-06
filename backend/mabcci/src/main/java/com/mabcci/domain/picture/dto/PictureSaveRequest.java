package com.mabcci.domain.picture.dto;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;
import java.util.List;

public final class PictureSaveRequest {

    @NotEmpty
    private List<MultipartFile> pictures;

    public PictureSaveRequest(final List<MultipartFile> pictures) {
        this.pictures = pictures;
    }

    public final List<MultipartFile> getPictures() {
        return pictures;
    }
}