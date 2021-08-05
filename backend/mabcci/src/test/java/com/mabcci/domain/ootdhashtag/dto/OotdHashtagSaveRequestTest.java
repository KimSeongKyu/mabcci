package com.mabcci.domain.ootdhashtag.dto;

import com.mabcci.domain.hashtag.domain.Hashtag;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static com.mabcci.domain.hashtag.domain.HashtagTest.HASHTAG;
import static com.mabcci.domain.ootd.domain.OotdTest.OOTD;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class OotdHashtagSaveRequestTest {

    public static final OotdHashtagSaveRequest OOTD_HASHTAG_SAVE_REQUEST =
            new OotdHashtagSaveRequest(OOTD, new ArrayList<>(List.of(HASHTAG, HASHTAG)));

    private OotdHashtagSaveRequest ootdHashtagSaveRequest;
    private List<Hashtag> hashtags;

    @BeforeEach
    void setUp() {
        hashtags = new ArrayList<>(List.of(HASHTAG, HASHTAG));
        ootdHashtagSaveRequest = new OotdHashtagSaveRequest(OOTD, hashtags);
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
                () -> assertThat(ootdHashtagSaveRequest.getOotd()).isEqualTo(OOTD),
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
