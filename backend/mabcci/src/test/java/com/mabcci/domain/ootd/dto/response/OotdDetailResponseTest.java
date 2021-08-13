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

import static com.mabcci.domain.member.domain.MemberTest.DESCRIPTION;
import static com.mabcci.domain.member.domain.MemberTest.PICTURE;
import static com.mabcci.global.common.EmailTest.EMAIL;
import static com.mabcci.global.common.NicknameTest.NICKNAME;
import static com.mabcci.global.common.PasswordTest.PASSWORD;
import static com.mabcci.global.common.PhoneTest.PHONE;
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
        ootdDetailResponse = OotdDetailResponse.ofOotd(ootd);

        ReflectionTestUtils.setField(ootd, "ootdLikes", List.of(ootdLike));
        ReflectionTestUtils.setField(ootd, "ootdPictures", ootdPictures);
        ReflectionTestUtils.setField(ootd, "ootdHashtags", ootdHashtags);
    }

    @DisplayName("OotdDetailResponse 인스턴스 생성 여부 테스트 작성")
    @Test
    void Initialize() {
        assertAll(
                () -> assertThat(ootdDetailResponse).isNotNull(),
                () -> assertThat(ootdDetailResponse).isExactlyInstanceOf(OotdDetailResponse.class)
        );
    }
}
