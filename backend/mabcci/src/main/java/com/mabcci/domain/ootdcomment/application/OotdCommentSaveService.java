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

import java.util.Objects;

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
        final Member member = getMemberByNickname(ootdCommentSaveRequest.nickname());
        final Ootd ootd = getOotdByOotdId(ootdCommentSaveRequest.ootdId());
        final OotdComment parentComment = getParentCommentByCommentId(ootdCommentSaveRequest.parentCommentId());

        validateParentCommentHasNoParent(parentComment);
        saveOotdComment(member, ootd, parentComment, ootdCommentSaveRequest.content());
    }

    private void saveOotdComment(final Member member, final Ootd ootd, final OotdComment parentComment, final String content) {
        ootdCommentRepository.save(OotdComment.builder()
                .member(member)
                .ootd(ootd)
                .content(ootdCommentSaveRequest.getContent())
                .parentComment(parentComment)
                .content(content)
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

    private void validateParentCommentHasNoParent(final OotdComment parentComment) {
        if (Objects.nonNull(parentComment) && parentComment.parentComment()
                .isPresent()) {
            throw new IllegalArgumentException();
        }
    }
}
