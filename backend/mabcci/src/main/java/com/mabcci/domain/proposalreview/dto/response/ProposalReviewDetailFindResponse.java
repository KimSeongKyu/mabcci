package com.mabcci.domain.proposalreview.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mabcci.domain.proposalreview.domain.ProposalReview;
import com.mabcci.domain.proposalreview.domain.StarRating;

import javax.validation.constraints.NotNull;

public final class ProposalReviewDetailFindResponse {

    @NotNull
    @JsonProperty("starRating")
    private StarRating starRating;

    @JsonProperty("content")
    private String content;

    private ProposalReviewDetailFindResponse() {
    }

    public final static ProposalReviewDetailFindResponse ofProposalReview(final ProposalReview proposalReview) {
        return new ProposalReviewDetailFindResponse(proposalReview.starRating(), proposalReview.content());
    }

    public ProposalReviewDetailFindResponse(final StarRating starRating, final String content) {
        this.starRating = starRating;
        this.content = content;
    }

    public final StarRating starRating() {
        return starRating;
    }

    public final String content() {
        return content;
    }
}
