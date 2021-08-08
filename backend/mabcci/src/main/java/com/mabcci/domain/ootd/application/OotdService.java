package com.mabcci.domain.ootd.application;

import com.mabcci.domain.member.domain.Member;
import com.mabcci.domain.member.domain.MemberRepository;
import com.mabcci.domain.ootd.domain.Ootd;
import com.mabcci.domain.ootd.domain.OotdRepository;
import com.mabcci.domain.ootd.dto.OotdListResponse;
import com.mabcci.domain.ootd.dto.OotdResponse;
import com.mabcci.domain.ootd.dto.OotdSaveRequest;
import com.mabcci.domain.ootdLike.domain.OotdLikeRepository;
import com.mabcci.domain.ootdhashtag.domain.OotdHashtagRepository;
import com.mabcci.domain.ootdpicture.domain.OotdPictureRepository;
import com.mabcci.global.common.Nickname;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OotdService {

    private final MemberRepository memberRepository;
    private final OotdRepository ootdRepository;
    private final OotdLikeRepository ootdLikeRepository;
    private final OotdPictureRepository ootdPictureRepository;
    private final OotdHashtagRepository ootdHashtagRepository;


    public OotdService(final MemberRepository memberRepository, final OotdRepository ootdRepository,
                       final OotdLikeRepository ootdLikeRepository, final OotdPictureRepository ootdPictureRepository,
                       final OotdHashtagRepository ootdHashtagRepository) {
        this.memberRepository = memberRepository;
        this.ootdRepository = ootdRepository;
        this.ootdLikeRepository = ootdLikeRepository;
        this.ootdPictureRepository = ootdPictureRepository;
        this.ootdHashtagRepository = ootdHashtagRepository;
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
    public OotdListResponse findFilteredOotdList(final String filter, final Pageable pageable) {
        final Page<Ootd> ootdPages = ootdRepository.findAll(pageable);
        final int totalPages = ootdPages.getTotalPages();
        final List<Ootd> ootds = ootdPages.toList();

        final List<OotdResponse> ootdResponses = ootds.stream()
                .map(ootd -> new OotdResponse(ootd.id(), ootd.member().nickname(),
                        ootdPictureRepository.findByOotd(ootd).path(), ootdHashtagRepository.findByOotd(ootd),
                        ootdLikeRepository.countByOotd(ootd)));

        return new OotdListResponse(ootdResponses, totalPages);
    }
}
