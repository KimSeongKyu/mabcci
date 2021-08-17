package com.mabcci.domain.proposal.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mabcci.domain.member.domain.Member;
import com.mabcci.domain.proposal.domain.Proposal;
import com.mabcci.global.common.Nickname;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDateTime;

public final class ProposalFindResponse {

    @JsonProperty("picture")
    private String picture;

    @Valid @NotNull @JsonProperty("nickname")
    private Nickname nickname;

    @NotNull @PastOrPresent @JsonProperty("createdDate")
    private LocalDateTime createdDate;

    private ProposalFindResponse() {
    }

    public final static ProposalFindResponse ofProposalAndMember(final Proposal proposal, final Member member) {
        return new ProposalFindResponse(member.picture(), member.nickname(), proposal.createdDate());
    }

    public ProposalFindResponse(final String picture, final Nickname nickname, final LocalDateTime createdDate) {
        this.picture = picture;
        this.nickname = nickname;
        this.createdDate = createdDate;
    }

    public final String picture() {
        return picture;
    }

    public final Nickname nickname() {
        return nickname;
    }

    public final LocalDateTime createdDate() {
        return createdDate;
    }
}
