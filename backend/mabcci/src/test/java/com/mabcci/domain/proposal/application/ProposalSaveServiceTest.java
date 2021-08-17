package com.mabcci.domain.proposal.application;

import com.mabcci.domain.member.domain.Gender;
import com.mabcci.domain.member.domain.Member;
import com.mabcci.domain.member.domain.MemberRepository;
import com.mabcci.domain.member.domain.MemberRole;
import com.mabcci.domain.picture.common.PictureUtil;
import com.mabcci.domain.picture.domain.Picture;
import com.mabcci.domain.proposal.domain.Proposal;
import com.mabcci.domain.proposal.domain.ProposalRepository;
import com.mabcci.domain.proposal.dto.ProposalSaveRequest;
import com.mabcci.global.common.Email;
import com.mabcci.global.common.Nickname;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

import static com.mabcci.domain.member.domain.MemberTest.DESCRIPTION;
import static com.mabcci.domain.member.domain.MemberTest.PICTURE;
import static com.mabcci.domain.picture.common.PictureUtilTest.PICTURE_FILES;
import static com.mabcci.global.common.EmailTest.EMAIL;
import static com.mabcci.global.common.NicknameTest.NICKNAME;
import static com.mabcci.global.common.PasswordTest.PASSWORD;
import static com.mabcci.global.common.PhoneTest.PHONE;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProposalSaveServiceTest {

    @InjectMocks private ProposalSaveService proposalSaveService;
    @Mock private ProposalRepository proposalRepository;
    @Mock private MemberRepository memberRepository;
    @Mock private PictureUtil pictureUtil;

    private Proposal proposal;
    private Member targetMember;
    private Member mabcci;
    private MultipartFile topFile;
    private MultipartFile bottomFile;
    private Picture topPicture;
    private Picture bottomPicture;

    @BeforeEach
    void setUp() {
        targetMember = Member.Builder()
                .email(EMAIL)
                .password(PASSWORD)
                .nickname(NICKNAME)
                .phone(PHONE)
                .gender(Gender.MAN)
                .description(DESCRIPTION)
                .picture(PICTURE)
                .memberRole(MemberRole.USER)
                .build();
        mabcci = Member.Builder()
                .email(Email.of("mabcci@example.com"))
                .password(PASSWORD)
                .nickname(Nickname.of("mabcci"))
                .phone(PHONE)
                .gender(Gender.MAN)
                .description(DESCRIPTION)
                .picture(PICTURE)
                .memberRole(MemberRole.MABCCI)
                .build();
        topFile = PICTURE_FILES.get(0);
        bottomFile = PICTURE_FILES.get(1);
        topPicture = new Picture("topPictureUrl", "topPictureFileName");
        bottomPicture = new Picture("bottomPictureUrl", "bottomPictureFileName");
        proposal = Proposal.builder()
                .targetMember(targetMember)
                .mabcci(mabcci)
                .top(topPicture.path())
                .bottom(bottomPicture.path())
                .build();
    }

    @DisplayName("ProposalSaveService 인스턴스 제안서 저장 테스트")
    @Test
    void save_proposal_test() {
        doReturn(Optional.of(targetMember), Optional.of(mabcci)).when(memberRepository).findByNickName(any());
        doReturn("testDirectory").when(pictureUtil).makeDirectory(any());
        doReturn(topPicture, bottomPicture).when(pictureUtil).savePicture(any(), any());
        doReturn(proposal).when(proposalRepository).save(any());

        final ProposalSaveRequest proposalSaveRequest = new ProposalSaveRequest(NICKNAME, Nickname.of("mabcci"), topFile, bottomFile, null, null, null);

        proposalSaveService.saveProposal(proposalSaveRequest);

        verify(memberRepository, times(2)).findByNickName(any());
        verify(pictureUtil, times(1)).makeDirectory(any());
        verify(pictureUtil, times(2)).savePicture(any(), any());
        verify(proposalRepository, times(1)).save(any());
    }
}
