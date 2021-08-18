package com.mabcci.domain.proposal.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mabcci.domain.member.domain.Member;
import com.mabcci.domain.proposal.domain.Proposal;
import com.mabcci.global.common.Nickname;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;

public final class ProposalFindResponse {

    @NotNull @Positive @JsonProperty("id")
    private Long id;

    @JsonProperty("picture")
    private String picture;

    @Valid @NotNull @JsonProperty("nickname")
    private Nickname nickname;

    @NotNull @PastOrPresent @JsonProperty("createdDate")
    private LocalDateTime createdDate;

    @NotNull @JsonProperty("isReviewed")
    private Boolean reviewed;

    private ProposalFindResponse() {
    }

    public final static ProposalFindResponse ofProposalAndMember(final Proposal proposal, final Member member) {
        return new ProposalFindResponse(proposal.id(), member.picture(), member.nickname(), proposal.createdDate(), proposal.isReviewed());
    }

    public ProposalFindResponse(final Long id, final String picture, final Nickname nickname, final LocalDateTime createdDate, final Boolean reviewed) {
        this.id = id;
        this.picture = picture;
        this.nickname = nickname;
        this.createdDate = createdDate;
        this.reviewed = reviewed;
    }

    public final Long id() {
        return id;
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

    public final Boolean isReviewed() {
        return reviewed;
    }
}
