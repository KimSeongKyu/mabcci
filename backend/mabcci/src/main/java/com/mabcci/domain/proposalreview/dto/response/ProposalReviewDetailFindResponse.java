package com.mabcci.domain.proposalreview.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mabcci.domain.proposalreview.domain.ProposalReview;
import com.mabcci.global.common.Nickname;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDateTime;

public final class ProposalReviewDetailFindResponse {

    @JsonProperty("memberPicture")
    private String memberPicture;

    @NotEmpty @JsonProperty("nickname")
    private Nickname nickname;

    @PositiveOrZero @JsonProperty("starRating")
    private int starRating;

    @JsonProperty("content")
    private String content;

    @PastOrPresent @JsonProperty("createdDate")
    private LocalDateTime createdDate;

    private ProposalReviewDetailFindResponse() {
    }

    public final static ProposalReviewDetailFindResponse ofProposalReview(final ProposalReview proposalReview) {
        return new ProposalReviewDetailFindResponse(proposalReview.proposal().targetMember().picture(),
                proposalReview.proposal().targetMember().nickname(), proposalReview.starRating().ordinal(),
                proposalReview.content(), proposalReview.createdDate());
    }

    public ProposalReviewDetailFindResponse(final String memberPicture, final Nickname nickname, final int starRating,
                                            final String content, final LocalDateTime createdDate) {
        this.memberPicture = memberPicture;
        this.nickname = nickname;
        this.starRating = starRating;
        this.content = content;
        this.createdDate = createdDate;
    }
}
