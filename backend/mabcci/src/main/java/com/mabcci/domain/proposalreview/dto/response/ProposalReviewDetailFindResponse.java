package com.mabcci.domain.proposalreview.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mabcci.domain.proposalreview.domain.ProposalReview;
import com.mabcci.domain.proposalreview.domain.StarRating;

import javax.validation.constraints.PositiveOrZero;

public final class ProposalReviewDetailFindResponse {

    @PositiveOrZero @JsonProperty("starRating")
    private int starRating;

    @JsonProperty("content")
    private String content;

    private ProposalReviewDetailFindResponse() {
    }

    public final static ProposalReviewDetailFindResponse ofProposalReview(final ProposalReview proposalReview) {
        return new ProposalReviewDetailFindResponse(proposalReview.starRating().ordinal(), proposalReview.content());
    }

    public ProposalReviewDetailFindResponse(final int starRating, final String content) {
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
