package com.mabcci.domain.ootdcomment.application;

import com.mabcci.domain.member.domain.Member;
import com.mabcci.domain.member.domain.MemberRepository;
import com.mabcci.domain.ootd.domain.Ootd;
import com.mabcci.domain.ootd.domain.OotdRepository;
import com.mabcci.domain.ootdcomment.domain.OotdComment;
import com.mabcci.domain.ootdcomment.domain.OotdCommentRepository;
import com.mabcci.domain.ootdcomment.dto.request.OotdCommentSaveRequest;
import com.mabcci.global.common.Nickname;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OotdCommentSaveService {

    private final MemberRepository memberRepository;
    private final OotdRepository ootdRepository;
    private final OotdCommentRepository ootdCommentRepository;

    public OotdCommentSaveService(final MemberRepository memberRepository, final OotdRepository ootdRepository,
                                  final OotdCommentRepository ootdCommentRepository) {
        this.memberRepository = memberRepository;
        this.ootdRepository = ootdRepository;
        this.ootdCommentRepository = ootdCommentRepository;
    }

    @Transactional
    public void saveOotdComment(final OotdCommentSaveRequest ootdCommentSaveRequest) {
        final Member member = getMemberByNickname(ootdCommentSaveRequest.getNickname());
        final Ootd ootd = getOotdByOotdId(ootdCommentSaveRequest.getOotdId());
        final OotdComment parentComment = getParentCommentByCommentId(ootdCommentSaveRequest.getParentCommentId());

        ootdCommentRepository.save(OotdComment.builder()
                .member(member)
                .ootd(ootd)
                .parentComment(parentComment)
                .build());
    }

    private Member getMemberByNickname(final Nickname nickname) {
        return memberRepository.findByNickName(nickname)
                .orElseThrow(IllegalArgumentException::new);
    }

    private Ootd getOotdByOotdId(final Long ootdId) {
        return ootdRepository.findById(ootdId)
                .orElseThrow(IllegalArgumentException::new);
    }

    private OotdComment getParentCommentByCommentId(final Long parentCommentId) {
        return ootdCommentRepository.findById(parentCommentId)
                .orElse(null);
    }
}
