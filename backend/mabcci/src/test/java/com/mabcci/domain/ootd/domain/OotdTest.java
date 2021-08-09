package com.mabcci.domain.ootd.domain;

import com.mabcci.domain.member.domain.Gender;
import com.mabcci.domain.member.domain.Member;
import com.mabcci.domain.member.domain.MemberRole;
import com.mabcci.domain.ootd.dto.OotdUpdateRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

import static com.mabcci.domain.member.domain.MemberTest.DESCRIPTION;
import static com.mabcci.domain.member.domain.MemberTest.PICTURE;
import static com.mabcci.global.common.EmailTest.EMAIL;
import static com.mabcci.global.common.NicknameTest.NICKNAME;
import static com.mabcci.global.common.PasswordTest.PASSWORD;
import static com.mabcci.global.common.PhoneTest.PHONE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class OotdTest {

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

    @DisplayName("Ootd 인스턴스 생성 여부 테스트")
    @Test
    void initialize() {
        assertAll(
                () -> assertThat(ootd).isNotNull(),
                () -> assertThat(ootd).isExactlyInstanceOf(Ootd.class)
        );
    }

    @DisplayName("Ootd 인스턴스 디폴트 생성자를 이용한 생성 여부 테스트")
    @Test
    void default_constructor_test() {
        final Ootd ootd = new Ootd();

        assertAll(
                () -> assertThat(ootd).isNotNull(),
                () -> assertThat(ootd).isExactlyInstanceOf(Ootd.class)
        );
    }

    @DisplayName("Ootd 인스턴스의 getter 메서드들 테스트")
    @Test
    void getter_test() {
        ReflectionTestUtils.setField(ootd, "id", 1L);

        assertAll(
                () -> assertThat(ootd.id()).isEqualTo(1L),
                () -> assertThat(ootd.member()).isEqualTo(member),
                () -> assertThat(ootd.content()).isEqualTo("content"),
                () -> assertThat(ootd.top()).isEqualTo("top"),
                () -> assertThat(ootd.bottom()).isEqualTo("bottom"),
                () -> assertThat(ootd.shoes()).isEqualTo("shoes"),
                () -> assertThat(ootd.accessory()).isEqualTo("accessory"),
                () -> assertThat(ootd.views()).isEqualTo(0L)
        );
    }

    @DisplayName("Ootd 인스턴스의 수정 테스트")
    @Test
    void update_test() {
        ReflectionTestUtils.setField(ootd, "id", 1L);
        final OotdUpdateRequest ootdUpdateRequest =
                new OotdUpdateRequest("내용", "상의", "하의", "신발", "악세사리");

        ootd = ootd.update(ootdUpdateRequest);

        assertAll(
                () -> assertThat(ootd.id()).isEqualTo(1L),
                () -> assertThat(ootd.member()).isEqualTo(member),
                () -> assertThat(ootd.content()).isEqualTo("내용"),
                () -> assertThat(ootd.top()).isEqualTo("상의"),
                () -> assertThat(ootd.bottom()).isEqualTo("하의"),
                () -> assertThat(ootd.shoes()).isEqualTo("신발"),
                () -> assertThat(ootd.accessory()).isEqualTo("악세사리"),
                () -> assertThat(ootd.views()).isEqualTo(0L)
        );
    }

    @DisplayName("Ootd 인스턴스의 프로퍼티 유효성 검증 테스트")
    @Test
    void validate_test() {
        final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        final Ootd invalidOotd = Ootd.builder()
                .views(-1L)
                .build();
        final int invalidPropertyCountOfValidOotd = 0;
        final int invalidPropertyCountOfInvalidOotd = 3;

        Set<ConstraintViolation<Ootd>> validationOfValidOotd = validator.validate(ootd);
        Set<ConstraintViolation<Ootd>> validationOfInvalidOotd = validator.validate(invalidOotd);

        assertAll(
                () -> assertThat(validationOfValidOotd.size()).isEqualTo(invalidPropertyCountOfValidOotd),
                () -> assertThat(validationOfInvalidOotd.size()).isEqualTo(invalidPropertyCountOfInvalidOotd)
        );
    }
}
