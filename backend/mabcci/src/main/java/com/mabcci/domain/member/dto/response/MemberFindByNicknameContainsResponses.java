package com.mabcci.domain.member.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import java.util.List;

public final class MemberFindByNicknameContainsResponses {

    @NotNull @JsonProperty("members")
    private List<MemberFindByNicknameContainsResponse> memberFindByNicknameContainsResponses;

    public MemberFindByNicknameContainsResponses(final List<MemberFindByNicknameContainsResponse> memberFindByNicknameContainsResponse) {
        this.memberFindByNicknameContainsResponses = memberFindByNicknameContainsResponse;
    }
}
