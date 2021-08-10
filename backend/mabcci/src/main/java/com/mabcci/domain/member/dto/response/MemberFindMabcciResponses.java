package com.mabcci.domain.member.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public final class MemberFindMabcciResponses {

    @JsonProperty("mabccies")
    private List<MemberFindMabcciResponse> mabcciResponses;

    MemberFindMabcciResponses() {
    }

    public MemberFindMabcciResponses(final List<MemberFindMabcciResponse> mabcciResponses) {
        this.mabcciResponses = mabcciResponses;
    }

    public List<MemberFindMabcciResponse> mabccies() {
        return mabcciResponses;
    }

}
