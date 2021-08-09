package com.mabcci.domain.picture.domain;

public enum PictureType {

    PROFILE("profile"),
    OOTD("ootd"),
    BOARD("board");

    private final String type;

    PictureType(final String type) {
        this.type = type;
    }

    public final String type() {
        return type;
    }
}
