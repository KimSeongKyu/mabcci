package com.mabcci.domain.proposal.domain;

import com.mabcci.domain.member.domain.Member;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Proposal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "proposal_id")
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "proposal_target_member_id", nullable = false, updatable = false)
    private Member targetMember;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "proposal_mabcci_id", nullable = false, updatable = false)
    private Member mabcci;

    @Column(name = "proposal_top", updatable = false)
    private String top;

    @Column(name = "proposal_bottom", updatable = false)
    private String bottom;

    @Column(name = "proposal_shoes", updatable = false)
    private String shoes;

    @Column(name = "proposal_accessory", updatable = false)
    private String accessory;

    @Column(name = "proposal_description", updatable = false)
    private String description;

    protected Proposal() {
    }

    protected Proposal(final ProposalBuilder proposalBuilder) {
        this.targetMember = proposalBuilder.targetMember;
        this.mabcci = proposalBuilder.mabcci;
        this.top = proposalBuilder.top;
        this.bottom = proposalBuilder.bottom;
        this.shoes = proposalBuilder.shoes;
        this.accessory = proposalBuilder.accessory;
        this.description = proposalBuilder.description;
    }

    public static ProposalBuilder builder() {
        return new ProposalBuilder();
    }

    public Long id() {
        return id;
    }

    public Member targetMember() {
        return targetMember;
    }

    public Member mabcci() {
        return mabcci;
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

    public static class ProposalBuilder {
        private Member targetMember;
        private Member mabcci;
        private String top;
        private String bottom;
        private String shoes;
        private String accessory;
        private String description;

        private ProposalBuilder() {
        }

        public ProposalBuilder targetMember(final Member targetMember) {
            this.targetMember = targetMember;
            return this;
        }

        public ProposalBuilder mabcci(final Member mabcci) {
            this.mabcci = mabcci;
            return this;
        }

        public ProposalBuilder top(final String top) {
            this.top = top;
            return this;
        }

        public ProposalBuilder bottom(final String bottom) {
            this.bottom = bottom;
            return this;
        }

        public ProposalBuilder shoes(final String shoes) {
            this.shoes = shoes;
            return this;
        }

        public ProposalBuilder accessory(final String accessory) {
            this.accessory = accessory;
            return this;
        }

        public ProposalBuilder description(final String description) {
            this.description = description;
            return this;
        }

        public Proposal build() {
            return new Proposal(this);
        }
    }
}
