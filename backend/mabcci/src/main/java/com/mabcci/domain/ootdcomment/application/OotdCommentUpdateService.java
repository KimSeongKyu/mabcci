package com.mabcci.domain.ootdcomment.application;

import com.mabcci.domain.ootdcomment.domain.OotdComment;
import com.mabcci.domain.ootdcomment.domain.OotdCommentRepository;
import com.mabcci.domain.ootdcomment.dto.request.OotdCommentUpdateRequest;
import com.mabcci.global.common.Nickname;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
public class OotdCommentUpdateService {

    private final OotdCommentRepository ootdCommentRepository;

    public OotdCommentUpdateService(final OotdCommentRepository ootdCommentRepository) {
        this.ootdCommentRepository = ootdCommentRepository;
    }

    @Transactional
    public void updateOotdComment(final Long id, final OotdCommentUpdateRequest ootdCommentUpdateRequest) {
        final OotdComment ootdComment = getOotdCommentById(id);
        validateMemberIsOotdCommentWriter(ootdComment, ootdCommentUpdateRequest.getNickname());
        ootdComment.update(ootdCommentUpdateRequest.getContent());
    }

    private OotdComment getOotdCommentById(final Long id) {
        return ootdCommentRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new);
    }

    private void validateMemberIsOotdCommentWriter(final OotdComment ootdComment, final Nickname nickname) {
        if(!ootdComment.member()
                .nickname()
                .equals(nickname)) {
            throw new IllegalArgumentException();
        }
    }
}
