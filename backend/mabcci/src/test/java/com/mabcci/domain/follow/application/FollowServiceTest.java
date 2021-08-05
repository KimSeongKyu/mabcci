package com.mabcci.domain.follow.application;

import com.mabcci.domain.follow.domain.FollowRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class FollowServiceTest {

    @Mock private FollowRepository followRepository;
    @InjectMocks private FollowService followService;

    @DisplayName("FollowService 인스턴스 생성 여부 테스트")
    @Test
    void constructor_test() {
        final FollowService followService = new FollowService(followRepository);

        assertAll(
                () -> assertThat(followService).isNotNull(),
                () -> assertThat(followService).isExactlyInstanceOf(FollowService.class)
        );
    }

}