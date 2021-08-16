package com.mabcci.domain.ootd.dto.response;

import com.mabcci.domain.hashtag.domain.Hashtag;
import com.mabcci.domain.member.domain.Gender;
import com.mabcci.domain.member.domain.Member;
import com.mabcci.domain.member.domain.MemberRole;
import com.mabcci.domain.ootd.domain.Ootd;
import com.mabcci.domain.ootdLike.domain.OotdLike;
import com.mabcci.domain.ootdhashtag.domain.OotdHashtag;
import com.mabcci.domain.ootdpicture.domain.OotdPicture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;
import java.util.stream.Collectors;

import static com.mabcci.domain.member.domain.MemberTest.DESCRIPTION;
import static com.mabcci.domain.member.domain.MemberTest.PICTURE;
import static com.mabcci.global.common.EmailTest.EMAIL;
import static com.mabcci.global.common.NicknameTest.NICKNAME;
import static com.mabcci.global.common.PasswordTest.PASSWORD;
import static com.mabcci.global.common.PhoneTest.PHONE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class OotdResponseTest {

    private OotdResponse ootdResponse;
    private Member member;
    private Ootd ootd;
    private List<OotdPicture> ootdPictures;
    private List<OotdHashtag> ootdHashtags;
    private List<OotdLike> ootdLikes;

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
        ootdPictures = List.of(OotdPicture
                .builder()
                .url("testUrl")
                .fileName("testFileName")
                .build());
        ootdHashtags = List.of(OotdHashtag.builder()
                .hashtag(Hashtag.builder()
                        .name("해시태그")
                        .build())
                .build());
        ootdLikes = List.of(OotdLike.builder()
                .member(member)
                .ootd(ootd)
                .build());
        ReflectionTestUtils.setField(ootd, "id", 1L);
        ReflectionTestUtils.setField(ootd, "ootdPictures", ootdPictures);
        ReflectionTestUtils.setField(ootd, "ootdHashtags", ootdHashtags);
        ReflectionTestUtils.setField(ootdLikes.get(0), "status", true);
        ReflectionTestUtils.setField(ootd, "ootdLikes", ootdLikes);
        ootdResponse = OotdResponse.ofOotd(ootd);
    }

    @DisplayName("OotdResponse 인스턴스 생성 여부 테스트")
    @Test
    void initialize() {
        assertAll(
                () -> assertThat(ootdResponse).isNotNull(),
                () -> assertThat(ootdResponse).isExactlyInstanceOf(OotdResponse.class)
        );
    }

    @DisplayName("OotdResponse 인스턴스 ootd의 대표 사진 경로 반환 테스트")
    @Test
    void get_representative_ootd_picture_path_test() {
       final String representativeOotdPicturePath =
               ReflectionTestUtils.invokeMethod(ootdResponse, "getRepresentativeOotdPicturePath", ootd);
       assertThat(representativeOotdPicturePath).isEqualTo(ootdPictures.get(0).path());
    }

    @DisplayName("OotdResponse 인스턴스 ootd 해시태그를 해시태그 이름으로 매핑 테스트")
    @Test
    void map_ootd_hashtags_to_hastag_names_test() {
        final List<String> hashtagNames =
                ReflectionTestUtils.invokeMethod(ootdResponse, "mapOotdHashtagsToHashtagNames", ootd);
        assertThat(hashtagNames).isEqualTo(List.of("해시태그"));
    }

    @DisplayName("OotdResponse 인스턴스 getter 메서드들 테스트")
    @Test
    void getter_test() {
        final String picturePath = ootdPictures.get(0).path();
        final List<String> hashtagNames = ootdHashtags.stream()
                .map(OotdHashtag::hashtag)
                .map(Hashtag::name)
                .collect(Collectors.toList());

        assertAll(
                () -> assertThat(ootdResponse.id()).isEqualTo(ootd.id()),
                () -> assertThat(ootdResponse.nickname()).isEqualTo(NICKNAME),
                () -> assertThat(ootdResponse.picture()).isEqualTo(picturePath),
                () -> assertThat(ootdResponse.hashtags()).isEqualTo(hashtagNames),
                () -> assertThat(ootdResponse.likeCount()).isEqualTo(1L)
        );
    }
}
