package com.mabcci.domain.proposal.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
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

    public ProposalFindResponse(final String picture, final Nickname nickname, final LocalDateTime createdDate) {
        this.picture = picture;
        this.nickname = nickname;
        this.createdDate = createdDate;
    }
}
