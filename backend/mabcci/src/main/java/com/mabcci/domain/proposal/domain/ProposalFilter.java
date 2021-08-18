package com.mabcci.domain.proposal.domain;

import com.mabcci.global.common.Nickname;

import java.util.List;

public enum ProposalFilter {

    RECEIVED() {
        public List<Proposal> getFilteredProposals(final ProposalRepository proposalRepository, final Nickname nickname) {
            return proposalRepository.findAllByTargetMemberNickname(nickname);
        }
    },
    SUGGESTED() {
        public List<Proposal> getFilteredProposals(final ProposalRepository proposalRepository, final Nickname nickname) {
            return proposalRepository.findAllByMabcciNickname(nickname);
        }
    };


    public abstract List<Proposal> getFilteredProposals(final ProposalRepository proposalRepository, final Nickname nickname);
}
