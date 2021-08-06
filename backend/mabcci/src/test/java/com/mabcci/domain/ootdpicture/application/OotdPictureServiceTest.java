package com.mabcci.domain.ootdpicture.application;

import com.mabcci.domain.member.domain.Gender;
import com.mabcci.domain.member.domain.Member;
import com.mabcci.domain.member.domain.MemberRole;
import com.mabcci.domain.ootd.domain.Ootd;
import com.mabcci.domain.ootdpicture.domain.OotdPicture;
import com.mabcci.domain.ootdpicture.domain.OotdPictureRepository;
import com.mabcci.domain.ootdpicture.dto.OotdPictureSaveRequest;
import com.mabcci.domain.picture.domain.Picture;
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
class OotdPictureServiceTest {

    @Mock private OotdPictureRepository ootdPictureRepository;
    @InjectMocks private OotdPictureService ootdPictureService;

    private OotdPicture ootdPicture;
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
        ootdPicture = OotdPicture.builder()
                .ootd(ootd)
                .url("testUrl")
                .fileName("testFileName")
                .build();
    }

    @DisplayName("OotdPictureService 인스턴스 ootd 사진 저장 테스트")
    @Test
    void register_ootd_picture_test() {
        final List<Picture> pictures = new ArrayList<>(List.of(
                new Picture("testDirectory", "testFileName"),
                new Picture("testDirectory", "testFileName")
        ));
        final OotdPictureSaveRequest ootdPictureSaveRequest =
                new OotdPictureSaveRequest(ootd, pictures);
        doReturn(ootdPicture).when(ootdPictureRepository).save(any());

        ootdPictureService.saveOotdPictures(ootdPictureSaveRequest);

        verify(ootdPictureRepository, times(pictures.size())).save(any());
    }
}
