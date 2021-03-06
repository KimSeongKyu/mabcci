package com.mabcci.domain.ootdpicture.domain;

import com.mabcci.domain.ootd.domain.Ootd;
import com.mabcci.domain.picture.domain.Picture;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class OotdPicture extends Picture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ootd_picture_id")
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ootd_picture_ootd_id", nullable = false)
    private Ootd ootd;

    protected OotdPicture() {
    }

    private OotdPicture(final OotdPictureBuilder ootdPictureBuilder) {
        super(ootdPictureBuilder.url, ootdPictureBuilder.fileName);
        this.ootd = ootdPictureBuilder.ootd;
    }

    public static OotdPictureBuilder builder() {
        return new OotdPictureBuilder();
    }

    public Long id() {
        return id;
    }

    public Ootd ootd() {
        return ootd;
    }

    public String url() {
        return super.url();
    }

    public String fileName() {
        return super.fileName();
    }

    public static class OotdPictureBuilder {
        private Ootd ootd;
        private String url;
        private String fileName;

        private OotdPictureBuilder() {
        }

        public OotdPictureBuilder picture(final Picture picture) {
            this.url = picture.url();
            this.fileName = picture.fileName();
            return this;
        }

        public OotdPictureBuilder ootd(final Ootd ootd) {
            this.ootd = ootd;
            return this;
        }

        public OotdPictureBuilder url(final String url) {
            this.url = url;
            return this;
        }

        public OotdPictureBuilder fileName(final String fileName) {
            this.fileName = fileName;
            return this;
        }

        public OotdPicture build() {
            return new OotdPicture(this);
        }
    }
}
