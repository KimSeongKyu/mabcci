package com.mabcci.domain.proposal.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mabcci.global.common.Nickname;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

public final class ProposalSaveRequest {

    @NotNull @JsonProperty("targetMemberNickname")
    private Nickname targetMemberNickname;

    @NotNull @JsonProperty("mabcciNickname")
    private Nickname mabcciNickname;

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

    public ProposalSaveRequest(final Nickname targetMemberNickname, final Nickname mabcciNickname, final MultipartFile top, final MultipartFile bottom,
                               final MultipartFile shoes, final MultipartFile accessory, final String description) {
        this.targetMemberNickname = targetMemberNickname;
        this.mabcciNickname = mabcciNickname;
        this.top = top;
        this.bottom = bottom;
        this.shoes = shoes;
        this.accessory = accessory;
        this.description = description;
    }

    public final Nickname targetMemberNickname() {
        return targetMemberNickname;
    }

    public final Nickname mabcciNickname() {
        return mabcciNickname;
    }

    public final MultipartFile top() {
        return top;
    }

    public final MultipartFile bottom() {
        return bottom;
    }

    public final MultipartFile shoes() {
        return shoes;
    }

    public final MultipartFile accessory() {
        return accessory;
    }

    public final String description() {
        return description;
    }
}

