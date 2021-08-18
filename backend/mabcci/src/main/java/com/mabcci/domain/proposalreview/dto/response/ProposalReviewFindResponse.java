package com.mabcci.domain.proposalreview.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mabcci.domain.proposalreview.domain.ProposalReview;

import javax.validation.constraints.PositiveOrZero;

public final class ProposalReviewFindResponse {

    @PositiveOrZero @JsonProperty("starRating")
    private int starRating;

    @JsonProperty("content")
    private String content;

    private ProposalReviewFindResponse() {
    }

    public final static ProposalReviewFindResponse ofProposalReview(final ProposalReview proposalReview) {
        return new ProposalReviewFindResponse(proposalReview.starRating().ordinal(), proposalReview.content());
    }

    public ProposalReviewFindResponse(final int starRating, final String content) {
        this.starRating = starRating;
        this.content = content;
    }

    public final int starRating() {
        return starRating;
    }

    public final String content() {
        return content;
    }
}
