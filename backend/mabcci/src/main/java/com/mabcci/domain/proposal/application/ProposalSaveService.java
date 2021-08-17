package com.mabcci.domain.proposal.application;

import com.mabcci.domain.member.domain.Member;
import com.mabcci.domain.member.domain.MemberRepository;
import com.mabcci.domain.picture.common.PictureUtil;
import com.mabcci.domain.picture.domain.PictureType;
import com.mabcci.domain.proposal.domain.Proposal;
import com.mabcci.domain.proposal.domain.ProposalRepository;
import com.mabcci.domain.proposal.dto.request.ProposalSaveRequest;
import com.mabcci.global.common.Nickname;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

import static com.mabcci.domain.proposal.dto.request.ProposalSaveRequest.TOP;
import static com.mabcci.domain.proposal.dto.request.ProposalSaveRequest.BOTTOM;
import static com.mabcci.domain.proposal.dto.request.ProposalSaveRequest.SHOES;
import static com.mabcci.domain.proposal.dto.request.ProposalSaveRequest.ACCESSORY;


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
        final Map<String, String> picturePaths = savePicturesAndMapToPath(proposalSaveRequest.pictures(), directoryName);

        saveProposal(targetMember, mabcci, picturePaths, proposalSaveRequest.description());
    }

    private Member getMemberByNickname(final Nickname nickname) {
        return memberRepository.findByNickName(nickname)
                .orElseThrow(IllegalArgumentException::new);
    }

    private Map<String, String> savePicturesAndMapToPath(final Map<String, MultipartFile> pictures, final String directoryName) {
        final Map<String, String> picturePaths = new HashMap<>();
        pictures.keySet()
                .stream()
                .forEach(part -> {
                    final String picturePath = savePicture(pictures.get(part), directoryName);
                    picturePaths.put(part, picturePath);
                });
        return picturePaths;
    }

    private String savePicture(final MultipartFile picture, final String directoryName) {
        return picture.isEmpty() ? null : pictureUtil.savePicture(picture, directoryName).path();
    }

    private void saveProposal(final Member targetMember, final Member mabcci,
                              final Map<String, String> picturePaths, final String description) {
        proposalRepository.save(buildProposal(targetMember, mabcci, picturePaths, description));
    }

    private Proposal buildProposal(final Member targetMember, final Member mabcci,
                                   final Map<String, String> picturePaths, final String description) {
        return Proposal.builder()
                .targetMember(targetMember)
                .mabcci(mabcci)
                .top(picturePaths.get(TOP))
                .bottom(picturePaths.get(BOTTOM))
                .shoes(picturePaths.get(SHOES))
                .accessory(picturePaths.get(ACCESSORY))
                .description(description)
                .build();
    }
}
