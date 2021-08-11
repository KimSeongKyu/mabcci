package com.mabcci.domain.ootd.dto.response;

import com.mabcci.domain.member.domain.Gender;
import com.mabcci.domain.member.domain.Member;
import com.mabcci.domain.member.domain.MemberRole;
import com.mabcci.domain.ootd.domain.Ootd;
import com.mabcci.domain.ootdpicture.domain.OotdPicture;
import com.mabcci.global.common.Nickname;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static com.mabcci.domain.member.domain.MemberTest.DESCRIPTION;
import static com.mabcci.domain.member.domain.MemberTest.PICTURE;
import static com.mabcci.global.common.EmailTest.EMAIL;
import static com.mabcci.global.common.NicknameTest.NICKNAME;
import static com.mabcci.global.common.PasswordTest.PASSWORD;
import static com.mabcci.global.common.PhoneTest.PHONE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class OotdListResponseTest {

    private Member member;
    private Ootd ootd;
    private OotdListResponse ootdListResponse;
    private List<OotdResponse> ootdResponses;

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
        ReflectionTestUtils.setField(ootd, "ootdPictures", List.of(
                OotdPicture
                        .builder()
                        .url("testUrl")
                        .fileName("testFileName")
                        .build()));
        ootdResponses = new ArrayList<>(List.of(new OotdResponse(ootd)));
        ootdListResponse = new OotdListResponse(ootdResponses, 1);
    }

    @DisplayName("OotdListResponse 인스턴스 생성 여부 테스트")
    @Test
    void initialize() {
        assertAll(
                () -> assertThat(ootdListResponse).isNotNull(),
                () -> assertThat(ootdListResponse).isExactlyInstanceOf(OotdListResponse.class)
        );
    }

    @DisplayName("OotdListResponse 인스턴스 getter 메서드들 테스트")
    @Test
    void getter_test() {
        assertAll(
                () -> assertThat(ootdListResponse.getOotdResponses()).isEqualTo(ootdResponses),
                () -> assertThat(ootdListResponse.getTotalPages()).isEqualTo(1L)
        );
    }

    @DisplayName("OotdListResponse 인스턴스 프로퍼티 유효성 검증 테스트")
    @Test
    void validate_test() {
        final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        final OotdListResponse invalidResponse = new OotdListResponse(new ArrayList<>(), -1);

        final Set<ConstraintViolation<OotdListResponse>> invalidPropertiesOfValidResponse =
                validator.validate(ootdListResponse);
        final Set<ConstraintViolation<OotdListResponse>> invalidPropertiesOfInvalidResponse =
                validator.validate(invalidResponse);

        assertAll(
                () -> assertThat(invalidPropertiesOfValidResponse.size()).isEqualTo(0),
                () -> assertThat(invalidPropertiesOfInvalidResponse.size()).isEqualTo(2)
        );
    }
}
