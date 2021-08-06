package com.mabcci.domain.like.domain;

<<<<<<< HEAD
import com.mabcci.domain.member.domain.Gender;
import com.mabcci.domain.member.domain.Member;
import com.mabcci.domain.member.domain.MemberRole;
=======
import com.mabcci.domain.category.domain.Category;
import com.mabcci.domain.member.domain.Member;
import com.mabcci.domain.member.domain.MemberSpecs;
>>>>>>> 026f3b2 ([S05P13C107-53] [BE-kwj1270] refactor: test 코드 static 의존성 제거)
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

<<<<<<< HEAD
=======
import static com.mabcci.domain.member.domain.Gender.MAN;
import static com.mabcci.domain.member.domain.MemberRole.USER;
>>>>>>> 026f3b2 ([S05P13C107-53] [BE-kwj1270] refactor: test 코드 static 의존성 제거)
import static com.mabcci.global.common.EmailTest.EMAIL;
import static com.mabcci.global.common.NicknameTest.NICKNAME;
import static com.mabcci.global.common.PasswordTest.PASSWORD;
import static com.mabcci.global.common.PhoneTest.PHONE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class LikeTest {

    private Member member;
    private Like like;
    private Member member;

    @BeforeEach
    void setUp() {
<<<<<<< HEAD
=======

>>>>>>> 026f3b2 ([S05P13C107-53] [BE-kwj1270] refactor: test 코드 static 의존성 제거)
        member = Member.Builder()
                .email(EMAIL)
                .password(PASSWORD)
                .nickname(NICKNAME)
                .phone(PHONE)
<<<<<<< HEAD
                .gender(Gender.MAN)
                .memberRole(MemberRole.USER)
=======
                .gender(MAN)
                .memberRole(USER)
>>>>>>> 026f3b2 ([S05P13C107-53] [BE-kwj1270] refactor: test 코드 static 의존성 제거)
                .build();
        like = new Like(member);
    }

    @DisplayName("Like 인스턴스 생성 여부 테스트")
    @Test
    void initialize() {
        assertAll(
                () -> assertThat(like).isNotNull(),
                () -> assertThat(like).isExactlyInstanceOf(Like.class)
        );
    }

    @DisplayName("Like 인스턴스 디폴트 생성자를 이용한 생성 테스트")
    @Test
    void default_constructor_test() {
        final Like like = new Like();

        assertAll(
                () -> assertThat(like).isNotNull(),
                () -> assertThat(like).isExactlyInstanceOf(Like.class)
        );
    }

    @DisplayName("Like 인스턴스 getter 메서드들 테스트")
    @Test
    void getter_test() {
        ReflectionTestUtils.setField(like, "status", true);

        assertAll(
                () -> assertThat(like.status()).isTrue(),
                () -> assertThat(like.member()).isEqualTo(member)
        );
    }

    @DisplayName("Like 인스턴스 프로퍼티 유효성 검증 테스트")
    @Test
    void validate_test() {
        final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        final Like invalidLike = new Like();

        final Set<ConstraintViolation<Like>> invalidPropertiesOfValidLike = validator.validate(like);
        final Set<ConstraintViolation<Like>> invalidPropertiesOfInvalidLike = validator.validate(invalidLike);

        assertAll(
                () -> assertThat(invalidPropertiesOfValidLike.size()).isEqualTo(0),
                () -> assertThat(invalidPropertiesOfInvalidLike.size()).isEqualTo(1)
        );
    }
}
