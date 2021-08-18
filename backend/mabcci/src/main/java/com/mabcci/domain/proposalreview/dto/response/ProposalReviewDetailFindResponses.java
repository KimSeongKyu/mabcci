package com.mabcci.domain.proposalreview.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import java.util.List;

public final class ProposalReviewDetailFindResponses {

    @NotNull @JsonProperty("proposalReviews")
    private List<ProposalReviewDetailFindResponse> proposalReviewDetailFindResponses;

    public ProposalReviewDetailFindResponses(final List<ProposalReviewDetailFindResponse> proposalReviewDetailFindResponses) {
        this.proposalReviewDetailFindResponses = proposalReviewDetailFindResponses;
    }

    public final List<ProposalReviewDetailFindResponse> proposalReviews() {
        return proposalReviewDetailFindResponses;
    }
}
