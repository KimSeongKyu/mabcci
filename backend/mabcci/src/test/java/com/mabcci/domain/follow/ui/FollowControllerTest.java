package com.mabcci.domain.follow.ui;

import com.mabcci.domain.follow.application.FollowService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class FollowControllerTest {

    @Mock private FollowService followService;
    @InjectMocks private FollowController followController;

    @DisplayName("FollowController 인스턴스 생성 여부 테스트")
    @Test
    void constructor_test() {
        final FollowController followController = new FollowController(followService);

        assertAll(
                () -> assertThat(followController).isNotNull(),
                () -> assertThat(followController).isExactlyInstanceOf(FollowController.class)
        );
    }
}