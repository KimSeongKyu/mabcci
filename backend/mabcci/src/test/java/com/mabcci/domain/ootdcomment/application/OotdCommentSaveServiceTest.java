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
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Optional;

import static com.mabcci.domain.member.domain.MemberTest.DESCRIPTION;
import static com.mabcci.domain.member.domain.MemberTest.PICTURE;
import static com.mabcci.global.common.EmailTest.EMAIL;
import static com.mabcci.global.common.NicknameTest.NICKNAME;
import static com.mabcci.global.common.PasswordTest.PASSWORD;
import static com.mabcci.global.common.PhoneTest.PHONE;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OotdCommentSaveServiceTest {

    @InjectMocks private OotdCommentSaveService ootdCommentSaveService;
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
                .content("??????")
                .build();
    }

    @DisplayName("OotdCommentSaveService ???????????? ootd ?????? ?????? ?????????")
    @Test
    void save_ootd_comment_test() {
        doReturn(Optional.of(member)).when(memberRepository).findByNickName(any());
        doReturn(Optional.of(ootd)).when(ootdRepository).findById(any());
        doReturn(Optional.empty()).when(ootdCommentRepository).findById(any());
        doReturn(ootdComment).when(ootdCommentRepository).save(any());

        final OotdCommentSaveRequest ootdCommentSaveRequest =
                new OotdCommentSaveRequest(ootd.id(), member.nickname(), null, "??????");
        ootdCommentSaveService.saveOotdComment(ootdCommentSaveRequest);

        verify(memberRepository, times(1)).findByNickName(any());
        verify(ootdRepository, times(1)).findById(any());
        verify(ootdCommentRepository, times(1)).findById(any());
        verify(ootdCommentRepository, times(1)).save(any());
    }

    @DisplayName("OotdCommentSaveService ???????????? ????????? ?????????????????? ?????? ?????????")
    @Test
    void validate_parent_comment_has_no_parent_test() {
        final OotdComment parentOotdComment = OotdComment.builder()
                .member(member)
                .ootd(ootd)
                .content("?????? ??????")
                .parentComment(ootdComment)
                .build();

        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() ->
                ReflectionTestUtils.invokeMethod(ootdCommentSaveService, "validateParentCommentHasNoParent", parentOotdComment)
        );
    }
}
