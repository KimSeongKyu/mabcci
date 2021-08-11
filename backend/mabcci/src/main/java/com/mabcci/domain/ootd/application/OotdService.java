package com.mabcci.domain.ootd.application;

import com.mabcci.domain.hashtag.domain.Hashtag;
import com.mabcci.domain.hashtag.domain.HashtagRepository;
import com.mabcci.domain.member.domain.Member;
import com.mabcci.domain.member.domain.MemberRepository;
import com.mabcci.domain.ootd.domain.Ootd;
import com.mabcci.domain.ootd.domain.OotdFilter;
import com.mabcci.domain.ootd.domain.OotdRepository;
import com.mabcci.domain.ootd.dto.request.OotdUpdateRequest;
import com.mabcci.domain.ootd.dto.request.OotdWithPicturesAndHashtagsRegisterRequest;
import com.mabcci.domain.ootd.dto.response.OotdListResponse;
import com.mabcci.domain.ootd.dto.response.OotdResponse;
import com.mabcci.domain.ootdLike.domain.OotdLike;
import com.mabcci.domain.ootdLike.domain.OotdLikeRepository;
import com.mabcci.domain.ootdhashtag.domain.OotdHashtag;
import com.mabcci.domain.ootdhashtag.domain.OotdHashtagRepository;
import com.mabcci.domain.ootdpicture.domain.OotdPicture;
import com.mabcci.domain.ootdpicture.domain.OotdPictureRepository;
import com.mabcci.domain.picture.common.PictureUtil;
import com.mabcci.domain.picture.domain.Picture;
import com.mabcci.domain.picture.domain.PictureType;
import com.mabcci.global.common.Nickname;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.toList;

@Service
public class OotdService {

    private final MemberRepository memberRepository;
    private final OotdRepository ootdRepository;
    private final OotdLikeRepository ootdLikeRepository;
    private final OotdPictureRepository ootdPictureRepository;
    private final HashtagRepository hashtagRepository;
    private final OotdHashtagRepository ootdHashtagRepository;
    private final PictureUtil pictureUtil;

    public OotdService(final MemberRepository memberRepository, final OotdRepository ootdRepository,
                       final OotdLikeRepository ootdLikeRepository, final OotdPictureRepository ootdPictureRepository,
                       final HashtagRepository hashtagRepository, final OotdHashtagRepository ootdHashtagRepository,
                       final PictureUtil pictureUtil) {
        this.memberRepository = memberRepository;
        this.ootdRepository = ootdRepository;
        this.ootdLikeRepository = ootdLikeRepository;
        this.ootdPictureRepository = ootdPictureRepository;
        this.hashtagRepository = hashtagRepository;
        this.ootdHashtagRepository = ootdHashtagRepository;
        this.pictureUtil = pictureUtil;
    }

    @Transactional
    public void saveOotdAndPicturesAndHashtags(final OotdWithPicturesAndHashtagsRegisterRequest request) {
        final Member member = findByNickName(request.getNickname());
        final Ootd ootd = saveOotd(request, member);

        final List<Picture> pictures = savePictures(request.getPictures());
        saveOotdPictures(ootd, pictures);

        final List<Hashtag> hashtags = saveHashtags(request.getHashtags());
        saveOotdHashtags(ootd, hashtags);
    }

    @Transactional(readOnly = true)
    public OotdListResponse findOotds(final Nickname nickname, final OotdFilter ootdFilter, final Pageable pageable) {
        final Member member = memberRepository.findByNickName(nickname)
                .orElseThrow(IllegalArgumentException::new);
        final Page<Ootd> ootdPages = ootdFilter.findOotds(ootdRepository, member, pageable);
        final int totalPages = ootdPages.getTotalPages();
        final List<Ootd> ootds = ootdPages.toList();
        final List<OotdResponse> ootdResponses = ootds.stream()
                .map(OotdResponse::new)
                .collect(toList());

        return new OotdListResponse(ootdResponses, totalPages);
    }

    @Transactional
    public void updateOotd(final Long id, final OotdUpdateRequest ootdUpdateRequest) {
        final Ootd ootd = ootdRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new);
        ootd.update(ootdUpdateRequest);
    }

    @Transactional
    public void deleteOotd(final Long id) {
        final Ootd ootd = ootdRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new);
        final List<OotdPicture> ootdPictures = ootdPictureRepository.findAllByOotd(ootd);

        ootdRepository.delete(ootd);
        ootdPictures.stream()
                .forEach(pictureUtil::deletePicture);
    }

    private Member findByNickName(final Nickname nickname) {
        return memberRepository.findByNickName(nickname)
                .orElseThrow(IllegalArgumentException::new);
    }

    private Ootd saveOotd(final OotdWithPicturesAndHashtagsRegisterRequest request, final Member member) {
        return ootdRepository.save(Ootd.builder()
                .member(member)
                .content(request.getContent())
                .top(request.getTop())
                .bottom(request.getBottom())
                .shoes(request.getShoes())
                .accessory(request.getAccessory())
                .build());
    }

    private List<Picture> savePictures(final List<MultipartFile> pictures) {
        final String directoryName = pictureUtil.makeDirectory(PictureType.OOTD);
        return pictures.stream()
                .map(picture -> pictureUtil.savePicture(picture, directoryName))
                .collect(toList());
    }

    private void saveOotdPictures(Ootd ootd, List<Picture> pictures) {
        pictures.stream()
                .map(picture -> OotdPicture.builder()
                        .picture(picture)
                        .ootd(ootd)
                        .build())
                .forEachOrdered(ootdPictureRepository::save);
    }

    private List<Hashtag> saveHashtags(final List<String> hashtags) {
        return hashtags.stream()
                .map(name -> {
                    final Optional<Hashtag> hashtag = hashtagRepository.findByName(name);
                    if (hashtag.isPresent()) {
                        return hashtag.get();
                    }
                    return hashtagRepository.save(Hashtag.builder()
                            .name(name)
                            .build());
                })
                .collect(toList());
    }

    private void saveOotdHashtags(final Ootd ootd, final List<Hashtag> hashtags) {
        hashtags.stream()
                .map(hashtag -> OotdHashtag.builder()
                        .ootd(ootd)
                        .hashtag(hashtag)
                        .build())
                .forEach(ootdHashtagRepository::save);
    }
}
