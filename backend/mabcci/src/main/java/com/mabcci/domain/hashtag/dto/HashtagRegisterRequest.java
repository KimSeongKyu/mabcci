package com.mabcci.domain.hashtag.dto;

import java.util.List;

public final class HashtagRegisterRequest {

    private List<String> names;

    private HashtagRegisterRequest() {
    }

    public HashtagRegisterRequest(final List<String> names) {
        this.names = names;
    }
}
