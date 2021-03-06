package com.mabcci.domain.ootdcomment.application;

import com.mabcci.domain.member.domain.Gender;
import com.mabcci.domain.member.domain.Member;
import com.mabcci.domain.member.domain.MemberRole;
import com.mabcci.domain.ootd.domain.Ootd;
import com.mabcci.domain.ootdcomment.domain.OotdComment;
import com.mabcci.domain.ootdcomment.domain.OotdCommentRepository;
import com.mabcci.domain.ootdcomment.dto.request.OotdCommentUpdateRequest;
import com.mabcci.global.common.Nickname;
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
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OotdCommentUpdateServiceTest {

    @InjectMocks private OotdCommentUpdateService ootdCommentUpdateService;
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

    @DisplayName("OotdCommentUpdateService ???????????? ootd ?????? ?????? ?????????")
    @Test
    void update_ootd_comment_test() {
        final String updatedContent = "????????? ??????";
        final OotdCommentUpdateRequest ootdCommentUpdateRequest = new OotdCommentUpdateRequest(NICKNAME, updatedContent);
        doReturn(Optional.of(ootdComment)).when(ootdCommentRepository).findById(any());

        ootdCommentUpdateService.updateOotdComment(1L, ootdCommentUpdateRequest);

        verify(ootdCommentRepository, times(1)).findById(any());
        assertThat(ootdComment.content()).isEqualTo(updatedContent);
    }

    @DisplayName("OotdCommentUpdateService ???????????? ootd ?????? ???????????? ?????? ??????????????? ?????? ?????????")
    @Test
    void validate_member_is_ootd_comment_writer_test() {
        final Nickname invalidNickname = Nickname.of("?????? ?????????");

        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() ->
                ReflectionTestUtils.invokeMethod(ootdCommentUpdateService, "validateMemberIsOotdCommentWriter", ootdComment, invalidNickname)
        );
    }
}
