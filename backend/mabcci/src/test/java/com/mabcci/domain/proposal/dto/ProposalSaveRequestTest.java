package com.mabcci.domain.proposal.dto;

import com.mabcci.global.common.Nickname;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import java.util.Set;

import static com.mabcci.domain.picture.common.PictureUtilTest.PICTURE_FILES;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class ProposalSaveRequestTest {

    private ProposalSaveRequest proposalSaveRequest;
    private Nickname targetMemeberNickname = Nickname.of("targetMemeber");
    private Nickname mabcciNickname = Nickname.of("mabcci");

    @BeforeEach
    void setUp() {

        proposalSaveRequest = new ProposalSaveRequest(targetMemeberNickname, mabcciNickname, PICTURE_FILES.get(0), null, null, null, null);
    }

    @DisplayName("ProposalSaveRequest 인스턴스 생성 여부 테스트")
    @Test
    void initialize() {
        assertAll(
                () -> assertThat(proposalSaveRequest).isNotNull(),
                () -> assertThat(proposalSaveRequest).isExactlyInstanceOf(ProposalSaveRequest.class)
        );
    }

    @DisplayName("ProposalSaveRequest 인스턴스 getter 메서드들 테스트")
    @Test
    void getter_test() {
        assertAll(
                () -> assertThat(proposalSaveRequest.targetMemberNickname()).isEqualTo(targetMemeberNickname),
                () -> assertThat(proposalSaveRequest.mabcciNickname()).isEqualTo(mabcciNickname),
                () -> assertThat(proposalSaveRequest.top()).isEqualTo(PICTURE_FILES.get(0)),
                () -> assertThat(proposalSaveRequest.bottom()).isNull(),
                () -> assertThat(proposalSaveRequest.shoes()).isNull(),
                () -> assertThat(proposalSaveRequest.accessory()).isNull(),
                () -> assertThat(proposalSaveRequest.description()).isNull()
        );
    }

    @DisplayName("ProposalSaveRequest 인스턴스 프로퍼티 유효성 검증 테스트")
    @Test
    void validate_test() {
        final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        final ProposalSaveRequest invalidRequest =
                new ProposalSaveRequest(targetMemeberNickname, mabcciNickname, null, null, null, null, null);

        final Set<ConstraintViolation<ProposalSaveRequest>> invalidPropertiesOfValidRequest =
                validator.validate(proposalSaveRequest);
        final Set<ConstraintViolation<ProposalSaveRequest>> invalidPropertiesOfInvalidRequest =
                validator.validate(invalidRequest);

        assertAll(
                () -> assertThat(invalidPropertiesOfValidRequest.size()).isEqualTo(0),
                () -> assertThat(invalidPropertiesOfInvalidRequest.size()).isEqualTo(2)
        );
    }
}
