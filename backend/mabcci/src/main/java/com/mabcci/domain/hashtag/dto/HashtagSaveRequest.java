package com.mabcci.domain.hashtag.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

public final class HashtagSaveRequest {

    @NotEmpty
    private List<String> names;

    private HashtagSaveRequest() {
    }

    public HashtagSaveRequest(final List<String> names) {
        this.names = names;
    }

    public final List<String> getNames() {
        return names;
    }
}
