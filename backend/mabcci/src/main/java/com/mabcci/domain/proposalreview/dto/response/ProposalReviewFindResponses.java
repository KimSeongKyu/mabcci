package com.mabcci.domain.proposalreview.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import java.util.List;

public final class ProposalReviewFindResponses {

    @NotNull @JsonProperty("proposalReviews")
    private List<ProposalReviewFindResponse> proposalReviewFindResponses;

    private ProposalReviewFindResponses() {
    }

    public ProposalReviewFindResponses(final List<ProposalReviewFindResponse> proposalReviewFindResponses) {
        this.proposalReviewFindResponses = proposalReviewFindResponses;
    }

    public final List<ProposalReviewFindResponse> proposalReviews() {
        return proposalReviewFindResponses;
    }
}
