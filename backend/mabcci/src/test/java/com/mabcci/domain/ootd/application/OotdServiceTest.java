package com.mabcci.domain.ootd.application;

import com.mabcci.domain.hashtag.domain.Hashtag;
import com.mabcci.domain.member.domain.Gender;
import com.mabcci.domain.member.domain.Member;
import com.mabcci.domain.member.domain.MemberRepository;
import com.mabcci.domain.member.domain.MemberRole;
import com.mabcci.domain.ootd.domain.Ootd;
import com.mabcci.domain.ootd.domain.OotdRepository;
import com.mabcci.domain.ootd.dto.OotdListResponse;
import com.mabcci.domain.ootd.dto.OotdSaveRequest;
import com.mabcci.domain.ootdLike.domain.OotdLikeRepository;
import com.mabcci.domain.ootdhashtag.domain.OotdHashtag;
import com.mabcci.domain.ootdhashtag.domain.OotdHashtagRepository;
import com.mabcci.domain.ootdpicture.domain.OotdPicture;
import com.mabcci.domain.ootdpicture.domain.OotdPictureRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.mabcci.domain.member.domain.MemberTest.DESCRIPTION;
import static com.mabcci.domain.member.domain.MemberTest.PICTURE;
import static com.mabcci.global.common.EmailTest.EMAIL;
import static com.mabcci.global.common.NicknameTest.NICKNAME;
import static com.mabcci.global.common.PasswordTest.PASSWORD;
import static com.mabcci.global.common.PhoneTest.PHONE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OotdServiceTest {

    @Mock private MemberRepository memberRepository;
    @Mock private OotdRepository ootdRepository;
    @InjectMocks private OotdService ootdService;

    @Mock
    private OotdPictureRepository ootdPictureRepository;

    @Mock
    private OotdHashtagRepository ootdHashtagRepository;

    @Mock
    private OotdLikeRepository ootdLikeRepository;

    private Member member;
    private Ootd ootd;
    private List<Ootd> ootds;
    private OotdPicture ootdPicture;
    private Hashtag firstHashtag;
    private Hashtag secondHashtag;
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
        ootds = new ArrayList<>(List.of(
                Ootd.builder()
                        .member(member)
                        .content("content")
                        .top("top")
                        .bottom("bottom")
                        .shoes("shoes")
                        .accessory("accessory")
                        .views(0L)
                        .build(),
                Ootd.builder()
                        .member(member)
                        .content("content")
                        .top("top")
                        .bottom("bottom")
                        .shoes("shoes")
                        .accessory("accessory")
                        .views(0L)
                        .build()
        ));
        ootdPicture = OotdPicture.builder()
                .url("testUrl")
                .fileName("testFileName")
                .build();
        firstHashtag = Hashtag.builder()
                .name("해시태그1")
                .build();
        secondHashtag = Hashtag.builder()
                .name("해시태그2")
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
    }

    @DisplayName("OotdService 인스턴스 ootd 저장 테스트")
    @Test
    void save_ootd_test() {
        final OotdSaveRequest ootdSaveRequest = new OotdSaveRequest("닉네임", "내용", "상의", "하의", "신발", "악세사리");
        doReturn(ootd).when(ootdRepository).save(any());
        doReturn(Optional.of(member)).when(memberRepository).findByNickName(any());

        ootdService.saveOotd(ootdSaveRequest);

        verify(ootdRepository, times(1)).save(any());
    }

    @DisplayName("OotdService 인스턴스 필터링된 ootd 리스트 조회 테스트")
    @Test
    void find_filtered_ootd_list_test() {
        final Page<Ootd> ootdPages = new PageImpl<>(ootds);
        doReturn(ootdPages).when(ootdRepository).findAll((Pageable) any());
        doReturn(Optional.of(ootdPicture)).when(ootdPictureRepository).findFirstByOotd(any());
        doReturn(ootdHashtags).when(ootdHashtagRepository).findByOotd(any());
        doReturn(1L).when(ootdLikeRepository).countByOotd(any());

        final OotdListResponse ootdListResponse = ootdService.findFilteredOotdList("all", PageRequest.of(1, 20));

        verify(ootdRepository, times(1)).findAll((Pageable) any());
        verify(ootdPictureRepository, times(2)).findFirstByOotd(any());
        verify(ootdHashtagRepository, times(2)).findByOotd(any());
        verify(ootdLikeRepository, times(2)).countByOotd(any());

        assertAll(
                () -> assertThat(ootdListResponse.getOotdResponses().size()).isEqualTo(2),
                () -> assertThat(ootdListResponse.getTotalPages()).isEqualTo(1)
        );
    }
}
