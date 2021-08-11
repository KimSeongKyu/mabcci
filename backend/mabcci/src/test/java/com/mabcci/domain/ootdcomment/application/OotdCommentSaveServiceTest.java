package com.mabcci.domain.ootdcomment.application;

import com.mabcci.domain.member.domain.Gender;
import com.mabcci.domain.member.domain.Member;
import com.mabcci.domain.member.domain.MemberRepository;
import com.mabcci.domain.member.domain.MemberRole;
import com.mabcci.domain.ootd.domain.Ootd;
import com.mabcci.domain.ootd.domain.OotdRepository;
import com.mabcci.domain.ootdcomment.domain.OotdComment;
import com.mabcci.domain.ootdcomment.domain.OotdCommentRepository;
import com.mabcci.domain.ootdcomment.dto.request.OotdCommentSaveRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.mabcci.domain.member.domain.MemberTest.DESCRIPTION;
import static com.mabcci.domain.member.domain.MemberTest.PICTURE;
import static com.mabcci.global.common.EmailTest.EMAIL;
import static com.mabcci.global.common.NicknameTest.NICKNAME;
import static com.mabcci.global.common.PasswordTest.PASSWORD;
import static com.mabcci.global.common.PhoneTest.PHONE;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OotdCommentSaveServiceTest {

    @InjectMocks private OotdCommentService ootdCommentService;
    @Mock private MemberRepository memberRepository;
    @Mock private OotdRepository ootdRepository;
    @Mock private OotdCommentRepository ootdCommentRepository;

    private Member member;
    private Ootd ootd;
    private OotdComment ootdComment;

    @BeforeEach
    void setUp() {
        member = Member.Builder()
                .email(EMAIL)
                .password(PASSWORD)
                .nickname(NICKNAME)
                .phone(PHONE)
                .gender(Gender.MAN)
                .description(DESCRIPTION)
                .picture(PICTURE)
                .memberRole(MemberRole.USER)
                .build();
        ootd = Ootd.builder()
                .member(member)
                .content("content")
                .top("top")
                .bottom("bottom")
                .shoes("shoes")
                .accessory("accessory")
                .views(0L)
                .build();
        ootdComment = OotdComment.builder()
                .member(member)
                .ootd(ootd)
                .content("내용")
                .build();
    }

    @DisplayName("OotdCommentSaveService 인스턴스 ootd 댓글 저장 테스트")
    @Test
    void save_ootd_comment_test() {
        doReturn(member).when(memberRepository).findByNickName(any());
        doReturn(ootd).when(ootdRepository).findById(any());
        doReturn(ootdComment).when(ootdCommentRepository).save(any());

        final OotdCommentSaveRequest ootdCommentSaveRequest =
                new OotdCommentSaveRequest(ootd.id(), member.nickname(), null, "내용");
        ootdCommentSaveService.saveOotdComment(ootdCommentSaveRequest);

        verify(memberRepository, times(1)).findByNickName(any());
        verify(ootdRepository, times(1)).findById(any());
        verify(ootdCommentRepository, times(1)).save(any());ㄱ
    }
}
