package com.mabcci.domain.ootdpicture.dto;

import com.mabcci.domain.picture.domain.Picture;

import java.util.List;

public final class OotdPictureRegisterRequest {

    private List<Picture> pictures;

    public OotdPictureRegisterRequest(final List<Picture> pictures) {
        this.pictures = pictures;
    }
}
