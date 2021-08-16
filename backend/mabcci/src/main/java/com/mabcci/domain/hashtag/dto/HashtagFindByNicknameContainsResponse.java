package com.mabcci.domain.hashtag.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import java.util.List;

public final class HashtagFindByNicknameContainsResponse {

    @NotNull @JsonProperty("hashtags")
    private List<String> hashtags;

    public HashtagFindByNicknameContainsResponse(final List<String> hashtags) {
        this.hashtags = hashtags;
    }

    public final List<String> hashtags() {
        return hashtags;
    }
}
