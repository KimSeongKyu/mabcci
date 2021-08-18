package com.mabcci.domain.hashtag.application;

import com.mabcci.domain.hashtag.domain.Hashtag;
import com.mabcci.domain.hashtag.domain.HashtagRepository;
import com.mabcci.domain.hashtag.dto.response.HashtagsFindByNameContainsResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class HashtagFindServiceTest {

    @InjectMocks private HashtagFindService hashtagFindService;
    @Mock private HashtagRepository hashtagRepository;

    private Hashtag hashtag;

    @BeforeEach
    void setUp() {
        hashtag = Hashtag.builder()
                .name("해시태그")
                .build();
    }

    @DisplayName("HashtagService 인스턴스 키워드를 이름에 포함한 Hashtag 리스트 검색 테스트")
    @Test
    void find_by_name_contains_test() {
        doReturn(List.of(hashtag)).when(hashtagRepository).findByNameContains(any());

        final HashtagsFindByNameContainsResponse hashtagsFindByNameContainsResponse =
                hashtagFindService.findByNameContains("해시태그");

        verify(hashtagRepository, times(1)).findByNameContains(any());
        assertThat(hashtagsFindByNameContainsResponse.hashtags()).contains("해시태그");
    }
}
