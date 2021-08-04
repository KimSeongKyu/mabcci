package com.mabcci.domain.ootdpicture.dto;

import com.mabcci.domain.ootd.domain.Ootd;
import com.mabcci.domain.picture.domain.Picture;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

public final class OotdPictureRegisterRequest {

    @Valid
    @NotNull
    private Ootd ootd;

    @NotEmpty
    private List<Picture> pictures;

    public OotdPictureRegisterRequest(@Valid final Ootd ootd, final List<Picture> pictures) {
        this.ootd = ootd;
        this.pictures = pictures;
    }

    public final Ootd getOotd() {
        return ootd;
    }

    public final List<Picture> getPictures() {
        return pictures;
    }
}
