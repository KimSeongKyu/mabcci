package com.mabcci.domain.hashtag.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import java.util.List;

public final class HashtagsFindByNameContainsResponse {

    @NotNull @JsonProperty("hashtags")
    private List<String> hashtags;

    private HashtagsFindByNameContainsResponse() {
    }

    public HashtagsFindByNameContainsResponse(final List<String> hashtags) {
        this.hashtags = hashtags;
    }

    public final List<String> hashtags() {
        return hashtags;
    }
}
