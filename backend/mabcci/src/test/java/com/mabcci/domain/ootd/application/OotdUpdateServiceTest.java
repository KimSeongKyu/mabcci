package com.mabcci.domain.ootd.application;

import com.mabcci.domain.member.domain.Gender;
import com.mabcci.domain.member.domain.Member;
import com.mabcci.domain.member.domain.MemberRole;
import com.mabcci.domain.ootd.domain.Ootd;
import com.mabcci.domain.ootd.domain.OotdRepository;
import com.mabcci.domain.ootd.dto.request.OotdUpdateRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

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
class OotdUpdateServiceTest {

    @InjectMocks private OotdUpdateService ootdUpdateService;
    @Mock private OotdRepository ootdRepository;

    private Member member;
    private Ootd ootd;

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
    }


    @DisplayName("OotdUpdateService 인스턴스 ootd 수정 테스트")
    @Test
    void update_ootd_test() {
        final OotdUpdateRequest ootdUpdateRequest =
                new OotdUpdateRequest("내용", "상의", "하의", "신발", "악세사리");

        doReturn(Optional.of(ootd)).when(ootdRepository).findById(any());

        ootdUpdateService.updateOotd(1L, ootdUpdateRequest);

        verify(ootdRepository, times(1)).findById(any());
        assertAll(
                () -> assertThat(ootd.content()).isEqualTo("내용"),
                () -> assertThat(ootd.top()).isEqualTo("상의"),
                () -> assertThat(ootd.bottom()).isEqualTo("하의"),
                () -> assertThat(ootd.shoes()).isEqualTo("신발"),
                () -> assertThat(ootd.accessory()).isEqualTo("악세사리")
        );
    }
}
