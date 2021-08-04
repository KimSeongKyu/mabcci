package com.mabcci.domain.ootdpicture.dto;

import com.mabcci.domain.picture.domain.Picture;

import javax.validation.constraints.NotEmpty;
import java.util.List;

public final class OotdPictureRegisterRequest {

    @NotEmpty
    private List<Picture> pictures;

    public OotdPictureRegisterRequest(final List<Picture> pictures) {
        this.pictures = pictures;
    }

    public final List<Picture> getPictures() {
        return pictures;
    }
}
