package com.mabcci.domain.proposalreview.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mabcci.domain.proposalreview.domain.StarRating;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public final class ProposalReviewSaveRequest {

    @NotNull @Positive @JsonProperty("id")
    private Long id;

    @NotNull @JsonProperty("starRating")
    private StarRating starRating;

    @JsonProperty("content")
    private String content;

    private ProposalReviewSaveRequest() {
    }

    public ProposalReviewSaveRequest(final Long id, final StarRating starRating, final String content) {
        this.id = id;
        this.starRating = starRating;
        this.content = content;
    }

    public Long id() {
        return id;
    }

    public StarRating starRating() {
        return starRating;
    }

    public String content() {
        return content;
    }
}
