package com.mabcci.domain.ootd.dto.response;

import com.mabcci.domain.hashtag.domain.Hashtag;
import com.mabcci.domain.member.domain.Gender;
import com.mabcci.domain.member.domain.Member;
import com.mabcci.domain.member.domain.MemberRole;
import com.mabcci.domain.ootd.domain.Ootd;
import com.mabcci.domain.ootdLike.domain.OotdLike;
import com.mabcci.domain.ootdhashtag.domain.OotdHashtag;
import com.mabcci.domain.ootdpicture.domain.OotdPicture;
import com.mabcci.domain.picture.domain.Picture;
import com.mabcci.global.common.Nickname;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import static com.mabcci.domain.member.domain.MemberTest.DESCRIPTION;
import static com.mabcci.domain.member.domain.MemberTest.PICTURE;
import static com.mabcci.global.common.EmailTest.EMAIL;
import static com.mabcci.global.common.NicknameTest.NICKNAME;
import static com.mabcci.global.common.PasswordTest.PASSWORD;
import static com.mabcci.global.common.PhoneTest.PHONE;
import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class OotdDetailResponseTest {

    private OotdDetailResponse ootdDetailResponse;
    private Member member;
    private Ootd ootd;
    private OotdPicture ootdPicture;
    private List<OotdPicture> ootdPictures;
    private OotdLike ootdLike;
    private Hashtag hashtag;
    private OotdHashtag firstOotdHashtag;
    private OotdHashtag secondOotdHashtag;
    private List<OotdHashtag> ootdHashtags;

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
        ootdPicture = OotdPicture.builder()
                .ootd(ootd)
                .url("testUrl")
                .fileName("testFileName")
                .build();
        ootdPictures = List.of(ootdPicture);
        hashtag = Hashtag.builder()
                .name("해시태그1")
                .build();
        firstOotdHashtag = OotdHashtag.builder()
                .ootd(ootd)
                .hashtag(hashtag)
                .build();
        secondOotdHashtag = OotdHashtag.builder()
                .ootd(ootd)
                .hashtag(hashtag)
                .build();
        ootdHashtags = List.of(firstOotdHashtag, secondOotdHashtag);
        ootdLike = OotdLike.builder()
                .member(member)
                .ootd(ootd)
                .build();

        ReflectionTestUtils.setField(ootdLike, "status", true);
        ReflectionTestUtils.setField(ootd, "ootdLikes", List.of(ootdLike));
        ReflectionTestUtils.setField(ootd, "ootdPictures", ootdPictures);
        ReflectionTestUtils.setField(ootd, "ootdHashtags", ootdHashtags);
        ReflectionTestUtils.setField(ootd, "createdDate", LocalDateTime.now());
        ReflectionTestUtils.setField(ootd, "modifiedDate", LocalDateTime.now());

        ootdDetailResponse = OotdDetailResponse.ofOotd(ootd);
    }

    @DisplayName("OotdDetailResponse 인스턴스 생성 여부 테스트")
    @Test
    void Initialize() {
        assertAll(
                () -> assertThat(ootdDetailResponse).isNotNull(),
                () -> assertThat(ootdDetailResponse).isExactlyInstanceOf(OotdDetailResponse.class)
        );
    }

    @DisplayName("OotdDetailResponse 인스턴스 getter 메서드들 테스트")
    @Test
    void getter_test() {
        final List<String> ootdPicturePaths = ootdPictures.stream()
                .map(Picture::path)
                .collect(toList());
        final List<String> hashtagNames = ootdHashtags.stream()
                .map(OotdHashtag::hashtag)
                .map(Hashtag::name)
                .collect(toList());

        assertAll(
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

    @DisplayName("OotdDetailResponse 인스턴스 프로퍼티 유효성 검증 테스트")
    @Test
    void validate_test() {
        final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        final OotdDetailResponse invalidResponse = new OotdDetailResponse("", Nickname.of(""),
                LocalDateTime.MAX, LocalDateTime.MAX, -1L, Collections.emptyList(), -1L,
                "", null, null, null, null, null);

        final Set<ConstraintViolation<OotdDetailResponse>> invalidPropertiesOfValidResponse =
                validator.validate(ootdDetailResponse);
        final Set<ConstraintViolation<OotdDetailResponse>> invalidPropertiesOfInvalidResponse =
                validator.validate(invalidResponse);

        assertAll(
                () -> assertThat(invalidPropertiesOfValidResponse.size()).isEqualTo(0),
                () -> assertThat(invalidPropertiesOfInvalidResponse.size()).isEqualTo(9)
        );
    }
}
