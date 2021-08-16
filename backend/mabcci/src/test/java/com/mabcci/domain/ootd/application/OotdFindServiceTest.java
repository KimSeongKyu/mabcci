package com.mabcci.domain.ootd.application;

import com.mabcci.domain.hashtag.domain.Hashtag;
import com.mabcci.domain.member.domain.Gender;
import com.mabcci.domain.member.domain.Member;
import com.mabcci.domain.member.domain.MemberRepository;
import com.mabcci.domain.member.domain.MemberRole;
import com.mabcci.domain.ootd.domain.Ootd;
import com.mabcci.domain.ootd.domain.OotdFilter;
import com.mabcci.domain.ootd.domain.OotdRepository;
import com.mabcci.domain.ootd.dto.response.OotdDetailResponse;
import com.mabcci.domain.ootd.dto.response.OotdListResponse;
import com.mabcci.domain.ootd.dto.response.OotdResponse;
import com.mabcci.domain.ootdLike.domain.OotdLike;
import com.mabcci.domain.ootdhashtag.domain.OotdHashtag;
import com.mabcci.domain.ootdpicture.domain.OotdPicture;
import com.mabcci.domain.picture.domain.Picture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.mabcci.domain.member.domain.MemberTest.DESCRIPTION;
import static com.mabcci.domain.member.domain.MemberTest.PICTURE;
import static com.mabcci.global.common.EmailTest.EMAIL;
import static com.mabcci.global.common.NicknameTest.NICKNAME;
import static com.mabcci.global.common.PasswordTest.PASSWORD;
import static com.mabcci.global.common.PhoneTest.PHONE;
import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OotdFindServiceTest {

    @InjectMocks private OotdFindService ootdFindService;
    @Mock private MemberRepository memberRepository;
    @Mock private OotdRepository ootdRepository;

    private Member member;
    private Ootd ootd;
    private Picture picture;
    private OotdPicture ootdPicture;
    private Hashtag firstHashtag;
    private OotdHashtag firstOotdHashtag;
    private OotdHashtag secondOotdHashtag;
    private List<OotdHashtag> ootdHashtags;
    private OotdLike ootdLike;

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
        picture = new Picture("testUrl", "testFileName");
        ootdPicture = OotdPicture.builder()
                .ootd(ootd)
                .picture(picture)
                .build();
        firstHashtag = Hashtag.builder()
                .name("해시태그1")
                .build();
        firstOotdHashtag = OotdHashtag.builder()
                .ootd(ootd)
                .hashtag(firstHashtag)
                .build();
        secondOotdHashtag = OotdHashtag.builder()
                .ootd(ootd)
                .hashtag(firstHashtag)
                .build();
        ootdHashtags = new ArrayList<>(List.of(firstOotdHashtag, secondOotdHashtag));
        ootdLike = OotdLike.builder()
                .member(member)
                .ootd(ootd)
                .build();
    }

    @DisplayName("OotdFindService 인스턴스 ootd 상세 조회 테스트")
    @Test
    void find_ootd_detail_test() {
        ReflectionTestUtils.setField(ootd, "id", 1L);
        ReflectionTestUtils.setField(ootd, "ootdPictures", List.of(ootdPicture));
        ReflectionTestUtils.setField(ootd, "ootdHashtags", ootdHashtags);
        ReflectionTestUtils.setField(ootdLike, "status", true);
        ReflectionTestUtils.setField(ootd, "ootdLikes", List.of(ootdLike));

        doReturn(Optional.of(ootd)).when(ootdRepository).findOotdDetailById(any());

        final OotdDetailResponse ootdDetailResponse = ootdFindService.findOotdDetail(1L, NICKNAME);

        verify(ootdRepository, times(1)).findOotdDetailById(any());

        final List<String> ootdPicturePaths = List.of(ootdPicture.path());
        final List<String> hashtagNames = ootdHashtags.stream()
                .map(OotdHashtag::hashtag)
                .map(Hashtag::name)
                .collect(toList());

        assertAll(
                () -> assertThat(ootd.views()).isEqualTo(1L),
                () -> assertThat(ootdDetailResponse.memberPicture()).isEqualTo(PICTURE),
                () -> assertThat(ootdDetailResponse.nickname()).isEqualTo(NICKNAME),
                () -> assertThat(ootdDetailResponse.createdDate()).isEqualTo(ootd.createdDate()),
                () -> assertThat(ootdDetailResponse.modifiedDate()).isEqualTo(ootd.modifiedDate()),
                () -> assertThat(ootdDetailResponse.views()).isEqualTo(ootd.views()),
                () -> assertThat(ootdDetailResponse.ootdPictures()).isEqualTo(ootdPicturePaths),
                () -> assertThat(ootdDetailResponse.likeCount()).isEqualTo(1L),
                () -> assertThat(ootdDetailResponse.content()).isEqualTo(ootd.content()),
                () -> assertThat(ootdDetailResponse.top()).isEqualTo(ootd.top()),
                () -> assertThat(ootdDetailResponse.bottom()).isEqualTo(ootd.bottom()),
                () -> assertThat(ootdDetailResponse.shoes()).isEqualTo(ootd.shoes()),
                () -> assertThat(ootdDetailResponse.accessory()).isEqualTo(ootd.accessory()),
                () -> assertThat(ootdDetailResponse.hashtags()).isEqualTo(hashtagNames)
        );
    }

    @DisplayName("OotdFindService 인스턴스 ootd 리스트 조회 테스트")
    @Test
    void find_ootds_test() {
        ReflectionTestUtils.setField(ootd, "id", 1L);
        ReflectionTestUtils.setField(ootd, "ootdPictures", List.of(ootdPicture));
        ReflectionTestUtils.setField(ootd, "ootdHashtags", ootdHashtags);
        ReflectionTestUtils.setField(ootdLike, "status", true);
        ReflectionTestUtils.setField(ootd, "ootdLikes", List.of(ootdLike));

        doReturn(Optional.of(member)).when(memberRepository).findByNickName(any());
        doReturn(new PageImpl<>(List.of(ootd))).when(ootdRepository).findOotds(any());

        final OotdListResponse ootdListResponse = ootdFindService.findOotds(NICKNAME, OotdFilter.ALL, PageRequest.of(0, 20));
        final OotdResponse ootdResponse = ootdListResponse.ootdResponses().get(0);
        final List<String> hashtagNames = ootdHashtags.stream()
                .map(OotdHashtag::hashtag)
                .map(Hashtag::name)
                .collect(Collectors.toList());

        verify(memberRepository, times(1)).findByNickName(any());
        verify(ootdRepository, times(1)).findOotds(any());

        assertAll(
                () -> assertThat(ootdResponse.id()).isEqualTo(ootd.id()),
                () -> assertThat(ootdResponse.nickname()).isEqualTo(NICKNAME),
                () -> assertThat(ootdResponse.picture()).isEqualTo(ootdPicture.path()),
                () -> assertThat(ootdResponse.hashtags()).isEqualTo(hashtagNames),
                () -> assertThat(ootdResponse.likeCount()).isEqualTo(1L)
        );
    }

    @DisplayName("OotdFindService 인스턴스 해시태그로 ootd 리스트 검색 테스트")
    @Test
    void find_ootds_by_hashtag_test() {
        ReflectionTestUtils.setField(ootd, "id", 1L);
        ReflectionTestUtils.setField(ootd, "ootdPictures", List.of(ootdPicture));
        ReflectionTestUtils.setField(ootd, "ootdHashtags", ootdHashtags);
        ReflectionTestUtils.setField(ootdLike, "status", true);
        ReflectionTestUtils.setField(ootd, "ootdLikes", List.of(ootdLike));

        doReturn(new PageImpl<>(List.of(ootd))).when(ootdRepository).findOotdsByHashtagName(any(), any());

        final OotdListResponse ootdListResponse = ootdFindService.findOotdsByHashtag("해시태그", OotdFilter.HASHTAG, PageRequest.of(0, 20));
        final OotdResponse ootdResponse = ootdListResponse.ootdResponses().get(0);

        verify(ootdRepository, times(1)).findOotdsByHashtagName(any(), any());

        assertAll(
                () -> assertThat(ootdResponse.id()).isEqualTo(ootd.id()),
                () -> assertThat(ootdResponse.nickname()).isEqualTo(NICKNAME),
                () -> assertThat(ootdResponse.picture()).isEqualTo(ootdPicture.path()),
                () -> assertThat(ootdResponse.hashtags()).contains(firstHashtag.name()),
                () -> assertThat(ootdResponse.likeCount()).isEqualTo(1L)
        );
    }
}
