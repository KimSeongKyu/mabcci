package com.mabcci.domain.picture.domain;


import com.mabcci.domain.BaseTimeEntity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;

@MappedSuperclass
public class Picture extends BaseTimeEntity {

    @NotBlank
    @Column(nullable = false, updatable = false)
    private String url;

    @NotBlank
    @Column(nullable = false, updatable = false)
    private String fileName;

    protected Picture() {
    }

    public Picture(final String url, final String fileName) {
        this.url = url;
        this.fileName = fileName;
    }

    public String url() {
        return url;
    }

    public String fileName() {
        return fileName;
    }
}
