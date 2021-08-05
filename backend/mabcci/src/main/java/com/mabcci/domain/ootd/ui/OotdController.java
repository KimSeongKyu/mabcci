package com.mabcci.domain.ootd.ui;

import com.mabcci.domain.hashtag.application.HashtagService;
import com.mabcci.domain.hashtag.dto.HashtagSaveRequest;
import com.mabcci.domain.hashtag.dto.HashtagSaveResponse;
import com.mabcci.domain.ootd.application.OotdService;
import com.mabcci.domain.ootd.domain.Ootd;
import com.mabcci.domain.ootd.dto.OotdSaveRequest;
import com.mabcci.domain.ootd.dto.OotdWithPicturesAndHashtagsRegisterRequest;
import com.mabcci.domain.ootdhashtag.application.OotdHashtagService;
import com.mabcci.domain.ootdhashtag.dto.OotdHashtagSaveRequest;
import com.mabcci.domain.ootdpicture.application.OotdPictureService;
import com.mabcci.domain.ootdpicture.dto.OotdPictureSaveRequest;
import com.mabcci.domain.picture.application.PictureService;
import com.mabcci.domain.picture.domain.Picture;
import com.mabcci.domain.picture.dto.PictureSaveRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@CrossOrigin(originPatterns = "http://localhost:*")
@RestController
public class OotdController {

    private final OotdService ootdService;
    private final PictureService pictureService;
    private final OotdPictureService ootdPictureService;
    private final HashtagService hashtagService;
    private final OotdHashtagService ootdHashtagService;

    public OotdController(final OotdService ootdService, final PictureService pictureService, final OotdPictureService ootdPictureService,
                          final HashtagService hashtagService, final OotdHashtagService ootdHashtagService) {
        this.ootdService = ootdService;
        this.pictureService = pictureService;
        this.ootdPictureService = ootdPictureService;
        this.hashtagService = hashtagService;
        this.ootdHashtagService = ootdHashtagService;
    }

    @PostMapping("/api/ootds")
    public ResponseEntity registerOotdWithPicturesAndHashtags(final OotdWithPicturesAndHashtagsRegisterRequest request) {
        final Ootd ootd = ootdService.saveOotd(new OotdSaveRequest(request.getNickname(), request.getContent(), request.getTop(),
                request.getBottom(), request.getShoes(), request.getAccessory()));

        final List<Picture> pictures = pictureService.savePictures(new PictureSaveRequest(request.getPictures()));
        ootdPictureService.saveOotdPictures(new OotdPictureSaveRequest(ootd, pictures));

        final HashtagSaveResponse hashtagSaveResponse = hashtagService.saveHashtags(new HashtagSaveRequest(request.getHashtags()));
        ootdHashtagService.saveOotdHashtags(new OotdHashtagSaveRequest(ootd, hashtagSaveResponse.getHashtags()));

        return ResponseEntity.noContent().build();
    }
}
