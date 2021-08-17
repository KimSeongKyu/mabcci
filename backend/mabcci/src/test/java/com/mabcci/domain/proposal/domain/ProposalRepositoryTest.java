package com.mabcci.domain.proposal.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@EnableJpaAuditing
@DataJpaTest
class ProposalRepositoryTest {

    @Autowired private ProposalRepository proposalRepository;

    @DisplayName("ProposalRepository 구현체 존재 여부 테스트")
    @Test
    void initialize() {
        assertAll(
                () -> assertThat(proposalRepository).isNotNull(),
                () -> assertThat(proposalRepository).isInstanceOf(ProposalRepository.class)
        );
    }
}
