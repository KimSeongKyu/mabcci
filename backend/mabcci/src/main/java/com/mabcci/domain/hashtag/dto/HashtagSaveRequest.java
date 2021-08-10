package com.mabcci.domain.hashtag.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

public final class HashtagSaveRequest {

    @NotEmpty
    @JsonProperty("names")
    private List<String> names;

    HashtagSaveRequest() {
    }

    public HashtagSaveRequest(final List<String> names) {
        this.names = names;
    }

    public final List<String> names() {
        return names;
    }

}
