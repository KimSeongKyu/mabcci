package com.mabcci.domain.ootd.application;

import com.mabcci.domain.member.domain.Member;
import com.mabcci.domain.member.domain.MemberRepository;
import com.mabcci.domain.ootd.domain.Ootd;
import com.mabcci.domain.ootd.domain.OotdFilter;
import com.mabcci.domain.ootd.domain.OotdRepository;
import com.mabcci.domain.ootd.dto.OotdListResponse;
import com.mabcci.domain.ootd.dto.OotdResponse;
import com.mabcci.domain.ootd.dto.OotdSaveRequest;
import com.mabcci.domain.ootd.dto.OotdUpdateRequest;
import com.mabcci.domain.ootdLike.domain.OotdLikeRepository;
import com.mabcci.domain.ootdhashtag.domain.OotdHashtagRepository;
import com.mabcci.domain.ootdpicture.domain.OotdPicture;
import com.mabcci.domain.ootdpicture.domain.OotdPictureRepository;
import com.mabcci.domain.picture.common.PictureUtil;
import com.mabcci.global.common.Nickname;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class OotdService {

    private final MemberRepository memberRepository;
    private final OotdRepository ootdRepository;
    private final OotdLikeRepository ootdLikeRepository;
    private final OotdPictureRepository ootdPictureRepository;
    private final OotdHashtagRepository ootdHashtagRepository;
    private final PictureUtil pictureUtil;


    public OotdService(final MemberRepository memberRepository, final OotdRepository ootdRepository,
                       final OotdLikeRepository ootdLikeRepository, final OotdPictureRepository ootdPictureRepository,
                       final OotdHashtagRepository ootdHashtagRepository, final PictureUtil pictureUtil) {
        this.memberRepository = memberRepository;
        this.ootdRepository = ootdRepository;
        this.ootdLikeRepository = ootdLikeRepository;
        this.ootdPictureRepository = ootdPictureRepository;
        this.ootdHashtagRepository = ootdHashtagRepository;
        this.pictureUtil = pictureUtil;
    }

    @Transactional
    public Ootd saveOotd(final OotdSaveRequest ootdSaveRequest) {
        final Member member = memberRepository.findByNickName(Nickname.of(ootdSaveRequest.getNickname()))
                .orElseThrow(IllegalArgumentException::new);
        final Ootd ootd = Ootd.builder()
                .member(member)
                .content(ootdSaveRequest.getContent())
                .top(ootdSaveRequest.getTop())
                .bottom(ootdSaveRequest.getBottom())
                .shoes(ootdSaveRequest.getShoes())
                .accessory(ootdSaveRequest.getAccessory())
                .build();
        return ootdRepository.save(ootd);
    }

    @Transactional(readOnly = true)
    public OotdListResponse findFilteredOotdList(final Nickname nickname, final OotdFilter ootdFilter, final Pageable pageable) {
        final Member member = memberRepository.findByNickName(nickname)
                .orElseThrow(IllegalArgumentException::new);
        final Page<Ootd> ootdPages = ootdFilter.findOotds(ootdRepository, member, pageable);
        final int totalPages = ootdPages.getTotalPages();
        final List<Ootd> ootds = ootdPages.toList();

        final List<OotdResponse> ootdResponses = ootds.stream()
                .map(ootd -> new OotdResponse(ootd.id(),
                        ootd.member()
                                .nickname()
                                .nickname(),
                        ootdPictureRepository.findFirstByOotd(ootd)
                                .orElseThrow(IllegalArgumentException::new).path(),
                        ootdHashtagRepository.findByOotd(ootd)
                                .stream()
                                .map(ootdHashtag -> ootdHashtag.hashtag().name())
                                .collect(toList()),
                        ootdLikeRepository.countByOotd(ootd)))
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
}
