package com.mabcci.domain.ootdhashtag.dto;

import com.mabcci.domain.hashtag.domain.Hashtag;
import com.mabcci.domain.ootd.domain.Ootd;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

public final class OotdHashtagSaveRequest {

    @NotNull
    private Ootd ootd;

    @NotEmpty
    private List<Hashtag> hashtags;

    private OotdHashtagSaveRequest() {
    }

    public OotdHashtagSaveRequest(final Ootd ootd, final List<Hashtag> hashtags) {
        this.ootd = ootd;
        this.hashtags = hashtags;
    }

    public final Ootd getOotd() {
        return ootd;
    }

    public final List<Hashtag> getHashtags() {
        return hashtags;
    }
}
