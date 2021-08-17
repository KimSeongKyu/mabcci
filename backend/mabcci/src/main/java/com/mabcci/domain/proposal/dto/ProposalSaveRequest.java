package com.mabcci.domain.proposal.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public final class ProposalSaveRequest {

    @NotNull @Positive @JsonProperty("targetMemberId")
    private Long targetMemberId;

    @NotNull @Positive @JsonProperty("mabcciId")
    private Long mabcciId;

    @JsonProperty("top")
    private MultipartFile top;

    @JsonProperty("bottom")
    private MultipartFile bottom;

    @JsonProperty("shoes")
    private MultipartFile shoes;

    @JsonProperty("accessory")
    private MultipartFile accessory;

    @JsonProperty("description")
    private String description;

    private ProposalSaveRequest() {
    }

    public ProposalSaveRequest(final Long targetMemberId, final Long mabcciId, final MultipartFile top, final MultipartFile bottom,
                               final MultipartFile shoes, final MultipartFile accessory, final String description) {
        this.targetMemberId = targetMemberId;
        this.mabcciId = mabcciId;
        this.top = top;
        this.bottom = bottom;
        this.shoes = shoes;
        this.accessory = accessory;
        this.description = description;
    }
}

