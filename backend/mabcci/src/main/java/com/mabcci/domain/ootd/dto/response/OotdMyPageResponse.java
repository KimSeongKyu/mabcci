package com.mabcci.domain.ootd.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mabcci.domain.ootd.domain.Ootd;
import com.mabcci.domain.picture.domain.Picture;

import java.time.LocalDateTime;

public final class OotdMyPageResponse implements Comparable<OotdMyPageResponse> {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("image")
    private String image;

    @JsonIgnore
    private LocalDateTime createdDate;

    public static final OotdMyPageResponse ofOotd(final Ootd ootd) {
        final Long id = ootd.id();
        final String image = ootd.ootdPictures().stream()
                .map(Picture::path)
                .findFirst()
                .orElse(null);
        final LocalDateTime createdDate = ootd.createdDate();
        return new OotdMyPageResponse(id, image, createdDate);
    }

    public OotdMyPageResponse(final Long id, final String image, final LocalDateTime createdDate) {
        this.id = id;
        this.image = image;
        this.createdDate = createdDate;
    }

    public Long id() {
        return id;
    }

    public String image() {
        return image;
    }

    @Override
    public int compareTo(final OotdMyPageResponse other) {
        return this.createdDate.compareTo(other.createdDate);
    }

}
