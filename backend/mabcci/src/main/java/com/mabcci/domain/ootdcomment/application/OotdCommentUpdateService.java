package com.mabcci.domain.ootdcomment.application;

import com.mabcci.domain.ootdcomment.domain.OotdComment;
import com.mabcci.domain.ootdcomment.domain.OotdCommentRepository;
import com.mabcci.domain.ootdcomment.dto.request.OotdCommentUpdateRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OotdCommentUpdateService {

    private final OotdCommentRepository ootdCommentRepository;

    public OotdCommentUpdateService(final OotdCommentRepository ootdCommentRepository) {
        this.ootdCommentRepository = ootdCommentRepository;
    }

    @Transactional
    public void updateOotdComment(final Long id, final OotdCommentUpdateRequest ootdCommentUpdateRequest) {
        final OotdComment ootdComment = getOotdCommentById(id);
        ootdComment.update(ootdCommentUpdateRequest.getContent());
    }

    private OotdComment getOotdCommentById(final Long id) {
        return ootdCommentRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new);
    }
}
