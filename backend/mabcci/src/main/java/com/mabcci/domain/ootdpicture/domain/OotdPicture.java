package com.mabcci.domain.ootdpicture.domain;

import com.mabcci.domain.ootd.domain.Ootd;
import com.mabcci.global.common.BaseTimeEntity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class OotdPicture extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ootd_picture_id")
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ootd_picture_ootd_id", nullable = false)
    private Ootd ootd;

    @NotBlank
    @Column(name = "ootd_picture_url", nullable = false)
    private String url;

    @NotBlank
    @Column(name = "ootd_picture_file_name", nullable = false)
    private String fileName;

    protected OotdPicture() {
    }

    private OotdPicture(final OotdPictureBuilder ootdPictureBuilder) {
        this.ootd = ootdPictureBuilder.ootd;
        this.url = ootdPictureBuilder.url;
        this.fileName = ootdPictureBuilder.fileName;
    }

    public static OotdPictureBuilder builder() {
        return new OotdPictureBuilder();
    }

    public static class OotdPictureBuilder {
        private Ootd ootd;
        private String url;
        private String fileName;

        private OotdPictureBuilder() {
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
