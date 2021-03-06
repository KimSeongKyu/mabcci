package com.mabcci.domain.ootd.application;

import com.mabcci.domain.member.domain.Gender;
import com.mabcci.domain.member.domain.Member;
import com.mabcci.domain.member.domain.MemberRole;
import com.mabcci.domain.ootd.domain.Ootd;
import com.mabcci.domain.ootd.domain.OotdRepository;
import com.mabcci.domain.ootdpicture.domain.OotdPicture;
import com.mabcci.domain.ootdpicture.domain.OotdPictureRepository;
import com.mabcci.domain.picture.common.PictureUtil;
import com.mabcci.domain.picture.domain.Picture;
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
import static com.mabcci.global.common.EmailTest.EMAIL;
import static com.mabcci.global.common.NicknameTest.NICKNAME;
import static com.mabcci.global.common.PasswordTest.PASSWORD;
import static com.mabcci.global.common.PhoneTest.PHONE;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OotdDeleteServiceTest {

    @InjectMocks private OotdDeleteService ootdDeleteService;
    @Mock private OotdRepository ootdRepository;
    @Mock private OotdPictureRepository ootdPictureRepository;
    @Mock private PictureUtil pictureUtil;

    private Member member;
    private Ootd ootd;
    private Picture picture;
    private OotdPicture ootdPicture;

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
    }

    @DisplayName("OotdDeleteServce ???????????? ootd ?????? ?????????")
    @Test
    void delete_ootd_test() {
        doReturn(Optional.of(ootd)).when(ootdRepository).findById(any());
        doReturn(List.of(ootdPicture)).when(ootdPictureRepository).findAllByOotd(any());
        doNothing().when(ootdRepository).delete(any());
        doNothing().when(pictureUtil).deletePicture(any());

        ootdDeleteService.deleteOotdById(1L);

        verify(ootdRepository, times(1)).findById(any());
        verify(ootdPictureRepository, times(1)).findAllByOotd(any());
        verify(ootdRepository, times(1)).delete(any());
        verify(pictureUtil, times(1)).deletePicture(any());
    }
}
