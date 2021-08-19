package com.mabcci.domain.member.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import java.util.List;

public final class MemberFindSimpleResponses {

    @NotNull @JsonProperty("members")
    private List<MemberFindSimpleResponse> memberSimpleRespons;

    public MemberFindSimpleResponses(final List<MemberFindSimpleResponse> memberFindSimpleResponse) {
        this.memberSimpleRespons = memberFindSimpleResponse;
    }

    public final List<MemberFindSimpleResponse> members() {
        return memberSimpleRespons;
    }
}
