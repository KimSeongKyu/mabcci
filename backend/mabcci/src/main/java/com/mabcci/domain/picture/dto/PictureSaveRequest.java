package com.mabcci.domain.picture.dto;

import com.mabcci.domain.picture.domain.PictureType;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

public final class PictureSaveRequest {

    @NotEmpty
    private List<MultipartFile> pictures;

    @NotNull
    private PictureType pictureType;

    public PictureSaveRequest(final List<MultipartFile> pictures, final PictureType pictureType) {
        this.pictures = pictures;
        this.pictureType = pictureType;
    }

    public final List<MultipartFile> getPictures() {
        return pictures;
    }

    public final PictureType getPictureType() {
        return pictureType;
    }
}
