package com.mabcci.domain.proposal.application;

import com.mabcci.domain.member.domain.Member;
import com.mabcci.domain.member.domain.MemberRepository;
import com.mabcci.domain.picture.common.PictureUtil;
import com.mabcci.domain.picture.domain.PictureType;
import com.mabcci.domain.proposal.domain.Proposal;
import com.mabcci.domain.proposal.domain.ProposalRepository;
import com.mabcci.domain.proposal.dto.ProposalSaveRequest;
import com.mabcci.global.common.Nickname;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
public class ProposalSaveService {

    private ProposalRepository proposalRepository;
    private MemberRepository memberRepository;
    private PictureUtil pictureUtil;

    public ProposalSaveService(final ProposalRepository proposalRepository, final MemberRepository memberRepository,
                               final PictureUtil pictureUtil) {
        this.proposalRepository = proposalRepository;
        this.memberRepository = memberRepository;
        this.pictureUtil = pictureUtil;
    }

    @Transactional
    public void saveProposal(final ProposalSaveRequest proposalSaveRequest) {
        final Member targetMember = getMemberByNickname(proposalSaveRequest.targetMemberNickname());
        final Member mabcci = getMemberByNickname(proposalSaveRequest.mabcciNickname());

        final String directoryName = pictureUtil.makeDirectory(PictureType.PROPOSAL);
        final String pathOfTopPicture = Objects.nonNull(proposalSaveRequest.top()) ?
                pictureUtil.savePicture(proposalSaveRequest.top(), directoryName).path() : null;
        final String pathOfBottomPicture = Objects.nonNull(proposalSaveRequest.bottom()) ?
                pictureUtil.savePicture(proposalSaveRequest.bottom(), directoryName).path() : null;
        final String pathOfShoesPicture = Objects.nonNull(proposalSaveRequest.shoes()) ?
                pictureUtil.savePicture(proposalSaveRequest.shoes(), directoryName).path() : null;
        final String pathOfAccessoryPicture = Objects.nonNull(proposalSaveRequest.accessory()) ?
                pictureUtil.savePicture(proposalSaveRequest.accessory(), directoryName).path() : null;

        saveProposal(targetMember, mabcci, pathOfTopPicture, pathOfBottomPicture, pathOfShoesPicture,
                pathOfAccessoryPicture, proposalSaveRequest.description());
    }

    private Member getMemberByNickname(final Nickname nickname) {
        return memberRepository.findByNickName(nickname)
                .orElseThrow(IllegalArgumentException::new);
    }

    private void saveProposal(final Member targetMember, final Member mabcci, final String pathOfTopPicture,
                              final String pathOfBottomPicture, final String pathOfShoesPicture,
                              final String pathOfAccessoryPicture, final String description) {
        proposalRepository.save(buildProposal(targetMember, mabcci, pathOfTopPicture, pathOfBottomPicture,
                pathOfShoesPicture, pathOfAccessoryPicture, description));
    }

    private Proposal buildProposal(final Member targetMember, final Member mabcci, final String pathOfTopPicture,
                                   final String pathOfBottomPicture, final String pathOfShoesPicture,
                                   final String pathOfAccessoryPicture, final String description) {
        return Proposal.builder()
                .targetMember(targetMember)
                .mabcci(mabcci)
                .top(pathOfTopPicture)
                .bottom(pathOfBottomPicture)
                .shoes(pathOfShoesPicture)
                .accessory(pathOfAccessoryPicture)
                .description(description)
                .build();
    }
}
