package com.mabcci.domain.ootdcomment.application;

import com.mabcci.domain.member.domain.Gender;
import com.mabcci.domain.member.domain.Member;
import com.mabcci.domain.member.domain.MemberRole;
import com.mabcci.domain.ootd.domain.Ootd;
import com.mabcci.domain.ootdcomment.domain.OotdComment;
import com.mabcci.domain.ootdcomment.domain.OotdCommentRepository;
import com.mabcci.domain.ootdcomment.dto.response.OotdCommentListFindResponse;
import com.mabcci.domain.ootdcomment.dto.response.OotdCommentFindResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;

import static com.mabcci.domain.member.domain.MemberTest.DESCRIPTION;
import static com.mabcci.domain.member.domain.MemberTest.PICTURE;
import static com.mabcci.global.common.EmailTest.EMAIL;
import static com.mabcci.global.common.NicknameTest.NICKNAME;
import static com.mabcci.global.common.PasswordTest.PASSWORD;
import static com.mabcci.global.common.PhoneTest.PHONE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OotdCommentFindServiceTest {

    @InjectMocks private OotdCommentFindService ootdCommentFindService;
    @Mock private OotdCommentRepository ootdCommentRepository;

    private Member member;
    private Ootd ootd;
    private OotdComment parentComment;
    private OotdComment childComment;
    private List<OotdComment> ootdComments;

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
        parentComment = OotdComment.builder()
                .member(member)
                .ootd(ootd)
                .content("상위댓글")
                .build();
        childComment = OotdComment.builder()
                .member(member)
                .ootd(ootd)
                .content("하위댓글")
                .parentComment(parentComment)
                .build();
        ootdComments = List.of(parentComment, childComment);
        ReflectionTestUtils.setField(parentComment, "id", 1L);
        ReflectionTestUtils.setField(childComment, "id", 2L);
    }


    @DisplayName("OotdCommentFindService 인스턴스 ootd 댓글 리스트 조회 테스트")
    @Test
    void find_ootd_comments_test() {
        final String parentCommentContent = "상위댓글";
        final String childCommentContent = "하위댓글";
        doReturn(ootdComments).when(ootdCommentRepository).findAllByOotdId(any());

        final OotdCommentListFindResponse ootdCommentListFindResponse = ootdCommentFindService.findOotdComments(2L);

        verify(ootdCommentRepository, times(1)).findAllByOotdId(any());

        final List<OotdCommentFindResponse> ootdCommentResponses = ootdCommentListFindResponse.comments();
        final OotdCommentFindResponse parentOotdCommentResponse = ootdCommentResponses.get(0);
        final OotdCommentFindResponse childOotdCommentResponse = ootdCommentResponses.get(1);

        assertAll(
                () -> assertThat(ootdCommentResponses.size()).isEqualTo(2),
                () -> assertThat(parentOotdCommentResponse.content()).isEqualTo(parentCommentContent),
                () -> assertThat(childOotdCommentResponse.content()).isEqualTo(childCommentContent)
        );
    }
}
