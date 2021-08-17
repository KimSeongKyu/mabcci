package com.mabcci.domain.proposal.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import java.util.List;

public final class ProposalFindResponses {

    @NotNull @JsonProperty("proposals")
    private List<ProposalFindResponse> proposalFindResponses;

    private ProposalFindResponses() {
    }

    public ProposalFindResponses(final List<ProposalFindResponse> proposalFindResponses) {
        this.proposalFindResponses = proposalFindResponses;
    }

    public final List<ProposalFindResponse> proposals() {
        return proposalFindResponses;
    }
}
