package com.mabcci.domain.ootd.application;

import com.mabcci.domain.hashtag.domain.Hashtag;
import com.mabcci.domain.hashtag.domain.HashtagRepository;
import com.mabcci.domain.member.domain.Member;
import com.mabcci.domain.member.domain.MemberRepository;
import com.mabcci.domain.ootd.domain.Ootd;
import com.mabcci.domain.ootd.domain.OotdRepository;
import com.mabcci.domain.ootd.dto.request.OotdWithPicturesAndHashtagsRegisterRequest;
import com.mabcci.domain.ootdhashtag.domain.OotdHashtag;
import com.mabcci.domain.ootdhashtag.domain.OotdHashtagRepository;
import com.mabcci.domain.ootdpicture.domain.OotdPicture;
import com.mabcci.domain.ootdpicture.domain.OotdPictureRepository;
import com.mabcci.domain.picture.common.PictureUtil;
import com.mabcci.domain.picture.domain.Picture;
import com.mabcci.domain.picture.domain.PictureType;
import com.mabcci.global.common.Nickname;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
public class OotdSaveService {

    private final MemberRepository memberRepository;
    private final OotdRepository ootdRepository;
    private final OotdPictureRepository ootdPictureRepository;
    private final HashtagRepository hashtagRepository;
    private final OotdHashtagRepository ootdHashtagRepository;
    private final PictureUtil pictureUtil;

    public OotdSaveService(final MemberRepository memberRepository, final OotdRepository ootdRepository,
                           final OotdPictureRepository ootdPictureRepository, final HashtagRepository hashtagRepository,
                           final OotdHashtagRepository ootdHashtagRepository, final PictureUtil pictureUtil) {
        this.memberRepository = memberRepository;
        this.ootdRepository = ootdRepository;
        this.ootdPictureRepository = ootdPictureRepository;
        this.hashtagRepository = hashtagRepository;
        this.ootdHashtagRepository = ootdHashtagRepository;
        this.pictureUtil = pictureUtil;
    }

    @Transactional
    public void saveOotdAndPicturesAndHashtags(final OotdWithPicturesAndHashtagsRegisterRequest request) {
        final Member member = findByNickName(request.nickname());
        final Ootd ootd = saveOotd(request, member);

        final String directoryName = pictureUtil.makeDirectory(PictureType.OOTD);
        final List<Picture> pictures = savePictures(request.pictures(), directoryName);
        saveOotdPictures(ootd, pictures);

        final List<Hashtag> hashtags = saveHashtags(request.hashtags());
        saveOotdHashtags(ootd, hashtags);
    }

    private Member findByNickName(final Nickname nickname) {
        return memberRepository.findByNickName(nickname)
                .orElseThrow(IllegalArgumentException::new);
    }

    private Ootd saveOotd(final OotdWithPicturesAndHashtagsRegisterRequest request, final Member member) {
        return ootdRepository.save(buildOotd(request, member));
    }

    private Ootd buildOotd(final OotdWithPicturesAndHashtagsRegisterRequest request, final Member member) {
        return Ootd.builder()
                .member(member)
                .content(request.content())
                .top(request.top())
                .bottom(request.bottom())
                .shoes(request.shoes())
                .accessory(request.accessory())
                .build();
    }

    private List<Picture> savePictures(final List<MultipartFile> pictures, final String directoryName) {
        return pictures.stream()
                .map(picture -> pictureUtil.savePicture(picture, directoryName))
                .collect(toList());
    }

    private void saveOotdPictures(final Ootd ootd, final List<Picture> pictures) {
        pictures.stream()
                .map(picture -> mapPictureToOotdPicture(picture, ootd))
                .forEachOrdered(ootdPictureRepository::save);
    }

    private OotdPicture mapPictureToOotdPicture(final Picture picture, final Ootd ootd) {
        return OotdPicture.builder()
                .picture(picture)
                .ootd(ootd)
                .build();
    }

    private List<Hashtag> saveHashtags(final List<String> hashtagNames) {
        return hashtagNames.stream()
                .map(this::getOrGenerateHashtag)
                .collect(toList());
    }

    private Hashtag getOrGenerateHashtag(final String name) {
        final Optional<Hashtag> hashtag = hashtagRepository.findByName(name);
        if (hashtag.isPresent()) {
            return hashtag.get();
        }
        return hashtagRepository.save(buildHashTag(name));
    }

    private Hashtag buildHashTag(final String name) {
        return Hashtag.builder()
                .name(name)
                .build();
    }

    private void saveOotdHashtags(final Ootd ootd, final List<Hashtag> hashtags) {
        hashtags.stream()
                .map(hashtag -> mapHashtagToOotdHashtag(hashtag, ootd))
                .forEach(ootdHashtagRepository::save);
    }

    private OotdHashtag mapHashtagToOotdHashtag(final Hashtag hashtag, final Ootd ootd) {
        return OotdHashtag.builder()
                .ootd(ootd)
                .hashtag(hashtag)
                .build();
    }
}
