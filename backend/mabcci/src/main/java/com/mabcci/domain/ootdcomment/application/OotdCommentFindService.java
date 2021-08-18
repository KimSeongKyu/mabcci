package com.mabcci.domain.ootdcomment.application;

import com.mabcci.domain.ootdcomment.domain.OotdCommentRepository;
import com.mabcci.domain.ootdcomment.dto.response.OotdCommentFindResponse;
import com.mabcci.domain.ootdcomment.dto.response.OotdCommentListFindResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static java.util.stream.Collectors.toList;

@Service
public class OotdCommentFindService {

    private final OotdCommentRepository ootdCommentRepository;

    public OotdCommentFindService(final OotdCommentRepository ootdCommentRepository) {
        this.ootdCommentRepository = ootdCommentRepository;
    }

    @Transactional
    public OotdCommentListFindResponse findOotdComments(final Long id) {
        return new OotdCommentListFindResponse(ootdCommentRepository.findAllByOotdId(id)
                .stream()
                .map(OotdCommentFindResponse::ofOotdComment)
                .collect(toList()));
    }
}
