package com.mabcci.domain.ootdhashtag.dto;

import com.mabcci.domain.hashtag.domain.Hashtag;
import com.mabcci.domain.member.domain.Gender;
import com.mabcci.domain.member.domain.Member;
import com.mabcci.domain.member.domain.MemberRole;
import com.mabcci.domain.ootd.domain.Ootd;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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

public class OotdHashtagSaveRequestTest {

    private OotdHashtagSaveRequest ootdHashtagSaveRequest;
    private Member member;
    private Ootd ootd;
    private List<Hashtag> hashtags;
    private Hashtag firstHashtag;
    private Hashtag secondHashtag;

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
        firstHashtag = Hashtag.builder()
                .name("해시태그1")
                .build();
        secondHashtag = Hashtag.builder()
                .name("해시태그2")
                .build();
        hashtags = new ArrayList<>(List.of(firstHashtag, secondHashtag));
        ootdHashtagSaveRequest = new OotdHashtagSaveRequest(ootd, hashtags);
    }

    @DisplayName("OotdHashtagSaveRequest 인스턴스 생성 여부 테스트")
    @Test
    void initialize() {
        assertAll(
                () -> assertThat(ootdHashtagSaveRequest).isNotNull(),
                () -> assertThat(ootdHashtagSaveRequest).isExactlyInstanceOf(OotdHashtagSaveRequest.class)
        );
    }

    @DisplayName("OotdHashtagSaveRequest 인스턴스 getter 메서드들 테스트")
    @Test
    void getter_test() {
        assertAll(
                () -> assertThat(ootdHashtagSaveRequest.getOotd()).isEqualTo(ootd),
                () -> assertThat(ootdHashtagSaveRequest.getHashtags()).isEqualTo(hashtags)
        );
    }

    @DisplayName("OotdHashtagSaveRequest 인스턴스 프로퍼티 유효성 검증 테스트")
    @Test
    void validate_test() {
        final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        final OotdHashtagSaveRequest invalidRequest = new OotdHashtagSaveRequest(null, new ArrayList<>());

        final Set<ConstraintViolation<OotdHashtagSaveRequest>> invalidPropertiesOfValidRequest =
                validator.validate(ootdHashtagSaveRequest);
        final Set<ConstraintViolation<OotdHashtagSaveRequest>> invalidPropertiesOfInValidRequest =
                validator.validate(invalidRequest);

        assertAll(
                () -> assertThat(invalidPropertiesOfValidRequest.size()).isEqualTo(0),
                () -> assertThat(invalidPropertiesOfInValidRequest.size()).isEqualTo(2)
        );

    }
}
