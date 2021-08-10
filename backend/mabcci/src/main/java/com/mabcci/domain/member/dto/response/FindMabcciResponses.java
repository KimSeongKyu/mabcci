package com.mabcci.domain.member.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public final class FindMabcciResponses {

    @JsonProperty("mabccies")
    private List<FindMabcciResponse> mabcciResponses;

    FindMabcciResponses() {
    }

    public FindMabcciResponses(final List<FindMabcciResponse> mabcciResponses) {
        this.mabcciResponses = mabcciResponses;
    }

    public List<FindMabcciResponse> mabccies() {
        return mabcciResponses;
    }

}
