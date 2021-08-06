package com.mabcci.domain.ootdhashtag.application;

import com.mabcci.domain.hashtag.domain.Hashtag;
import com.mabcci.domain.member.domain.Gender;
import com.mabcci.domain.member.domain.Member;
import com.mabcci.domain.member.domain.MemberRole;
import com.mabcci.domain.ootd.domain.Ootd;
import com.mabcci.domain.ootdhashtag.domain.OotdHashtag;
import com.mabcci.domain.ootdhashtag.domain.OotdHashtagRepository;
import com.mabcci.domain.ootdhashtag.dto.OotdHashtagSaveRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static com.mabcci.domain.member.domain.MemberTest.DESCRIPTION;
import static com.mabcci.domain.member.domain.MemberTest.PICTURE;
import static com.mabcci.global.common.EmailTest.EMAIL;
import static com.mabcci.global.common.NicknameTest.NICKNAME;
import static com.mabcci.global.common.PasswordTest.PASSWORD;
import static com.mabcci.global.common.PhoneTest.PHONE;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OotdHashtagServiceTest {

    @InjectMocks
    private OotdHashtagService ootdHashtagService;

    @Mock
    private OotdHashtagRepository ootdHashtagRepository;

    private OotdHashtagSaveRequest ootdHashtagSaveRequest;
    private OotdHashtag ootdHashtag;
    private List<Hashtag> hashtags;
    private Hashtag firstHashtag;
    private Hashtag secondHashtag;
    private Member member;
    private Ootd ootd;

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
        firstHashtag = Hashtag.builder()
                .name("해시태그1")
                .build();
        secondHashtag = Hashtag.builder()
                .name("해시태그2")
                .build();
        hashtags = new ArrayList<>(List.of(firstHashtag, secondHashtag));
        ootdHashtagSaveRequest = new OotdHashtagSaveRequest(ootd, hashtags);
    }


    @DisplayName("OotdHashtagService 인스턴스 OotdHashtags 저장 테스트")
    @Test
    void save_ootd_hashtags_test() {
        doReturn(ootdHashtag).when(ootdHashtagRepository).save(any());

        ootdHashtagService.saveOotdHashtags(ootdHashtagSaveRequest);

        verify(ootdHashtagRepository, times(ootdHashtagSaveRequest.getHashtags().size())).save(any());
    }
}
