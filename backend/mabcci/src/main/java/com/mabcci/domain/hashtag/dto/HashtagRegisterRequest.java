package com.mabcci.domain.hashtag.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

public final class HashtagRegisterRequest {

    @NotEmpty
    private List<String> names;

    private HashtagRegisterRequest() {
    }

    public HashtagRegisterRequest(final List<String> names) {
        this.names = names;
    }

    public final List<String> getNames() {
        return names;
    }
}
