package com.mabcci.domain.hashtag.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mabcci.domain.hashtag.domain.Hashtag;

import javax.validation.constraints.NotEmpty;
import java.util.List;

public final class HashtagSaveResponse {

    @NotEmpty
    @JsonProperty("hashtags")
    private List<Hashtag> hashtags;

    HashtagSaveResponse() {
    }

    public HashtagSaveResponse(final List<Hashtag> hashtags) {
        this.hashtags = hashtags;
    }

    public final List<Hashtag> hashtags() {
        return hashtags;
    }

}
