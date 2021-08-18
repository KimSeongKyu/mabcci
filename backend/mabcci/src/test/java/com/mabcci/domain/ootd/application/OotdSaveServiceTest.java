package com.mabcci.domain.ootd.application;

import com.mabcci.domain.hashtag.domain.Hashtag;
import com.mabcci.domain.hashtag.domain.HashtagRepository;
import com.mabcci.domain.member.domain.Gender;
import com.mabcci.domain.member.domain.Member;
import com.mabcci.domain.member.domain.MemberRepository;
import com.mabcci.domain.member.domain.MemberRole;
import com.mabcci.domain.ootd.domain.Ootd;
import com.mabcci.domain.ootd.domain.OotdRepository;
import com.mabcci.domain.ootd.dto.request.OotdWithPicturesAndHashtagsRegisterRequest;
import com.mabcci.domain.ootdhashtag.domain.OotdHashtag;
import com.mabcci.domain.ootdhashtag.domain.OotdHashtagRepository;
import com.mabcci.domain.ootdpicture.domain.OotdPicture;
import com.mabcci.domain.ootdpicture.domain.OotdPictureRepository;
import com.mabcci.domain.picture.common.PictureUtil;
import com.mabcci.domain.picture.domain.Picture;
import com.mabcci.global.common.Nickname;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static com.mabcci.domain.member.domain.MemberTest.DESCRIPTION;
import static com.mabcci.domain.member.domain.MemberTest.PICTURE;
import static com.mabcci.domain.picture.common.PictureUtilTest.PICTURE_FILES;
import static com.mabcci.global.common.EmailTest.EMAIL;
import static com.mabcci.global.common.NicknameTest.NICKNAME;
import static com.mabcci.global.common.PasswordTest.PASSWORD;
import static com.mabcci.global.common.PhoneTest.PHONE;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OotdSaveServiceTest {

    @InjectMocks private OotdSaveService ootdSaveService;
    @Mock private MemberRepository memberRepository;
    @Mock private OotdRepository ootdRepository;
    @Mock private OotdPictureRepository ootdPictureRepository;
    @Mock private HashtagRepository hashtagRepository;
    @Mock private OotdHashtagRepository ootdHashtagRepository;
    @Mock private PictureUtil pictureUtil;

    private Member member;
    private Ootd ootd;
    private Picture picture;
    private OotdPicture ootdPicture;
    private Hashtag hashtag;
    private OotdHashtag ootdHashtag;

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
        hashtag = Hashtag.builder()
                .name("해시태그1")
                .build();
        ootdHashtag = OotdHashtag.builder()
                .ootd(ootd)
                .hashtag(hashtag)
                .build();
    }

    @DisplayName("OotdSaveService 인스턴스 ootd, 사진, 해시태그 저장 테스트")
    @Test
    void save_ootd_and_pictures_and_hashtags_test() {
        final OotdWithPicturesAndHashtagsRegisterRequest request =
                new OotdWithPicturesAndHashtagsRegisterRequest(Nickname.of("닉네임"), "내용", "상의", "하의", "신발", "악세사리",
                        PICTURE_FILES, List.of("해시태그1", "해시태그2"));

        doReturn(Optional.of(member)).when(memberRepository).findByNickName(any());
        doReturn(ootd).when(ootdRepository).save(any());
        doReturn(picture).when(pictureUtil).savePicture(any(), any());
        doReturn("directory").when(pictureUtil).makeDirectory(any());
        doReturn(ootdPicture).when(ootdPictureRepository).save(any());
        doReturn(Optional.empty()).when(hashtagRepository).findByName(any());
        doReturn(hashtag).when(hashtagRepository).save(any());
        doReturn(ootdHashtag).when(ootdHashtagRepository).save(any());

        ootdSaveService.saveOotdAndPicturesAndHashtags(request);

        verify(memberRepository, times(1)).findByNickName(any());
        verify(ootdRepository, times(1)).save(any());
        verify(pictureUtil, times(1)).makeDirectory(any());
        verify(pictureUtil, times(2)).savePicture(any(), any());
        verify(ootdPictureRepository, times(2)).save(any());
        verify(hashtagRepository, times(2)).findByName(any());
        verify(hashtagRepository, times(2)).save(any());
        verify(ootdHashtagRepository, times(2)).save(any());
    }
}
