package com.mabcci.domain.ootdcomment.application;

import com.mabcci.domain.ootdcomment.domain.OotdCommentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OotdCommentDeleteService {

    private final OotdCommentRepository ootdCommentRepository;

    public OotdCommentDeleteService(final OotdCommentRepository ootdCommentRepository) {
        this.ootdCommentRepository = ootdCommentRepository;
    }

    @Transactional
    public void deleteOotdComment(final Long id) {
        ootdCommentRepository.deleteById(id);
    }
}
