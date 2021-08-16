package com.mabcci.domain.ootd.application;

import com.mabcci.domain.member.domain.Member;
import com.mabcci.domain.member.domain.MemberRepository;
import com.mabcci.domain.ootd.domain.Ootd;
import com.mabcci.domain.ootd.domain.OotdFilter;
import com.mabcci.domain.ootd.domain.OotdRepository;
import com.mabcci.domain.ootd.dto.response.OotdDetailResponse;
import com.mabcci.domain.ootd.dto.response.OotdListResponse;
import com.mabcci.domain.ootd.dto.response.OotdResponse;
import com.mabcci.domain.ootdLike.domain.OotdLike;
import com.mabcci.global.common.Nickname;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class OotdFindService {

    private final MemberRepository memberRepository;
    private final OotdRepository ootdRepository;

    public OotdFindService(final MemberRepository memberRepository, final OotdRepository ootdRepository) {
        this.memberRepository = memberRepository;
        this.ootdRepository = ootdRepository;
    }


    @Transactional(readOnly = true)
    public OotdListResponse findOotds(final Nickname nickname, final OotdFilter ootdFilter, final Pageable pageable) {
        final Member member = getMemberByNickname(nickname);
        final Page<Ootd> ootdPages = ootdFilter.getFilteredOotds(ootdRepository, member, pageable);
        final int totalPages = ootdPages.getTotalPages();

        return new OotdListResponse(getOotdResponses(ootdPages.toList()), totalPages);
    }

    @Transactional
    public OotdDetailResponse findOotdDetail(final Long id, final Nickname nickname) {
        final Ootd ootd = getOotdById(id);
        ootd.increaseViews();
        return OotdDetailResponse.ofOotdAndLikeStatus(ootd, isLikedOotd(ootd, nickname));
    }

    private boolean isLikedOotd(final Ootd ootd, final Nickname nickname) {
        return ootd.ootdLikes()
                .stream()
                .filter(OotdLike::status)
                .map(OotdLike::member)
                .map(Member::nickname)
                .anyMatch(LikedMemberNickname -> LikedMemberNickname.equals(nickname));
    }

    private Ootd getOotdById(final Long id) {
        return ootdRepository.findOotdDetailById(id)
                .orElseThrow(IllegalArgumentException::new);
    }

    private Member getMemberByNickname(final Nickname nickname) {
        return memberRepository.findByNickName(nickname)
                .orElseThrow(IllegalArgumentException::new);
    }

    private List<OotdResponse> getOotdResponses(final List<Ootd> ootds) {
        return ootds.stream()
                .map(OotdResponse::ofOotd)
                .collect(toList());
    }

    public OotdListResponse findOotdsByKeyword(final String hashtagName, final OotdFilter ootdFilter, final Pageable pageable) {
        final Page<Ootd> pageOotds = ootdFilter.getFilteredOotds(ootdRepository, hashtagName, pageable);
        final int totalPages = pageOotds.getTotalPages();

        return new OotdListResponse(getOotdResponses(pageOotds.toList()), totalPages);
    }
}
