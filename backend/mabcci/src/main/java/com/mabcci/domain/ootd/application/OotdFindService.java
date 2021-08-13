package com.mabcci.domain.ootd.application;

import com.mabcci.domain.member.domain.Member;
import com.mabcci.domain.member.domain.MemberRepository;
import com.mabcci.domain.ootd.domain.Ootd;
import com.mabcci.domain.ootd.domain.OotdFilter;
import com.mabcci.domain.ootd.domain.OotdRepository;
import com.mabcci.domain.ootd.dto.response.OotdDetailResponse;
import com.mabcci.domain.ootd.dto.response.OotdListResponse;
import com.mabcci.domain.ootd.dto.response.OotdResponse;
import com.mabcci.global.common.Nickname;
import org.springframework.data.domain.Page;
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
        final List<Ootd> ootds = ootdPages.toList();
        final List<OotdResponse> ootdResponses = getOotdResponses(ootds);

        return new OotdListResponse(ootdResponses, totalPages);
    }

    @Transactional(readOnly = true)
    public OotdDetailResponse findOotdDetail(final Long id) {
        return OotdDetailResponse.ofOotd(getOotdById(id));
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
                .map(OotdResponse::new)
                .collect(toList());
    }
}
