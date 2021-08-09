package com.mabcci.domain.ootdhashtag.domain;

import com.mabcci.domain.hashtag.domain.Hashtag;
import com.mabcci.domain.member.domain.Gender;
import com.mabcci.domain.member.domain.Member;
import com.mabcci.domain.member.domain.MemberRole;
import com.mabcci.domain.ootd.domain.Ootd;
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

public class OotdHashtagTest {

    private OotdHashtag ootdHashtag;
    private Hashtag hashtag;
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
        hashtag = Hashtag.builder()
                .name("해시태그")
                .build();
        ootdHashtag = OotdHashtag.builder()
                .ootd(ootd)
                .hashtag(hashtag)
                .build();
    }

    @DisplayName("OotdHashtag 인스턴스 생성 여부 테스트")
    @Test
    void initialize() {
        assertAll(
                () -> assertThat(ootdHashtag).isNotNull(),
                () -> assertThat(ootdHashtag).isExactlyInstanceOf(OotdHashtag.class)
        );
    }

    @DisplayName("OotdHashtag 인스턴스 디폴트 생성자를 이용한 생성 테스트")
    @Test
    void default_constructor_test() {
        final OotdHashtag ootdHashtag = new OotdHashtag();

        assertAll(
                () -> assertThat(ootdHashtag).isNotNull(),
                () -> assertThat(ootdHashtag).isExactlyInstanceOf(OotdHashtag.class)
        );
    }

    @DisplayName("OotdHashtag 인스턴스 getter 메서드들 테스트")
    @Test
    void getter_test() {
        ReflectionTestUtils.setField(ootdHashtag, "id", 1L);

        assertAll(
                () -> assertThat(ootdHashtag.id()).isEqualTo(1L),
                () -> assertThat(ootdHashtag.ootd()).isEqualTo(ootd),
                () -> assertThat(ootdHashtag.hashtag()).isEqualTo(hashtag)
        );
    }

    @DisplayName("OotdHashtag 인스턴스 프로퍼티 유효성 검증 테스트")
    @Test
    void validate_test() {
        final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        final OotdHashtag invalidOotdHashtag = OotdHashtag.builder().build();

        final Set<ConstraintViolation<OotdHashtag>> invalidPropertiesOfValidOotdHashtag =
                validator.validate(ootdHashtag);
        final Set<ConstraintViolation<OotdHashtag>> invalidPropertiesOfInvalidOotdHashtag =
                validator.validate(invalidOotdHashtag);

        assertAll(
                () -> assertThat(invalidPropertiesOfValidOotdHashtag.size()).isEqualTo(0),
                () -> assertThat(invalidPropertiesOfInvalidOotdHashtag.size()).isEqualTo(2)
        );
    }
}
