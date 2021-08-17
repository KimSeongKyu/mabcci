package com.mabcci.domain.proposal.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mabcci.domain.proposal.domain.Proposal;
import com.mabcci.global.common.Nickname;

public final class ProposalDetailFindResponse {

    @JsonProperty("targetMemberNickname")
    private Nickname targetMemberNickname;

    @JsonProperty("mabcciNickname")
    private Nickname mabcciNickname;

    @JsonProperty("top")
    private String top;

    @JsonProperty("bottom")
    private String bottom;

    @JsonProperty("shoes")
    private String shoes;

    @JsonProperty("accessory")
    private String accessory;

    @JsonProperty("description")
    private String description;

    private ProposalDetailFindResponse() {
    }

    public final static ProposalDetailFindResponse ofProposal(final Proposal proposal) {
        return new ProposalDetailFindResponse(proposal.targetMember().nickname(), proposal.mabcci().nickname(),
                proposal.top(), proposal.bottom(), proposal.shoes(), proposal.accessory(), proposal.description());
    }

    public ProposalDetailFindResponse(final Nickname targetMemberNickname, final Nickname mabcciNickname,
                                      final String top, final String bottom, final String shoes,
                                      final String accessory, final String description) {
        this.targetMemberNickname = targetMemberNickname;
        this.mabcciNickname = mabcciNickname;
        this.top = top;
        this.bottom = bottom;
        this.shoes = shoes;
        this.accessory = accessory;
        this.description = description;
    }

    public Nickname targetMemberNickname() {
        return targetMemberNickname;
    }

    public Nickname mabcciNickname() {
        return mabcciNickname;
    }

    public String top() {
        return top;
    }

    public String bottom() {
        return bottom;
    }

    public String shoes() {
        return shoes;
    }

    public String accessory() {
        return accessory;
    }

    public String description() {
        return description;
    }
}
