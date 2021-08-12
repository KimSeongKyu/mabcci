package com.mabcci.domain.ootdcomment.application;

import com.mabcci.domain.ootdcomment.domain.OotdComment;
import com.mabcci.domain.ootdcomment.domain.OotdCommentRepository;
import com.mabcci.domain.ootdcomment.dto.response.OotdCommentListResponse;
import com.mabcci.domain.ootdcomment.dto.response.OotdCommentResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.function.Predicate;

import static java.util.stream.Collectors.toList;

import static java.util.stream.Collectors.groupingBy;

@Service
public class OotdCommentFindService {

    private final OotdCommentRepository ootdCommentRepository;

    public OotdCommentFindService(final OotdCommentRepository ootdCommentRepository) {
        this.ootdCommentRepository = ootdCommentRepository;
    }

    @Transactional
    public OotdCommentListResponse findOotdComments(final Long id) {
        final List<OotdComment> ootdComments = ootdCommentRepository.findAllByOotdId(id);

        final Map<Optional<OotdComment>, List<OotdComment>> childrenOotdCommentsMappedByParent = ootdComments.stream()
                .collect(groupingBy(OotdComment::parentComment));

        final List<OotdCommentResponse> childrenOotdCommentResponses =
                getOotdCommentResponses(childrenOotdCommentsMappedByParent, Collections.emptyList(), Optional::isPresent);

        final List<OotdCommentResponse> parentOotdCommentResponses =
                getOotdCommentResponses(childrenOotdCommentsMappedByParent, childrenOotdCommentResponses, Optional::isEmpty);

        return new OotdCommentListResponse(parentOotdCommentResponses);
    }

    private List<OotdCommentResponse> getOotdCommentResponses(final Map<Optional<OotdComment>, List<OotdComment>> childrenOotdCommentsMappedByParent,
                                                              final List<OotdCommentResponse> childrenOotdCommentResponses,
                                                              final Predicate<? super Optional> predicate) {
        return childrenOotdCommentsMappedByParent.keySet()
                .stream()
                .filter(predicate)
                .map(childrenOotdCommentsMappedByParent::get)
                .flatMap(List::stream)
                .map(ootdComment -> OotdCommentResponse.ofOotdCommentWithChildren(ootdComment, childrenOotdCommentResponses))
                .collect(toList());
    }
}
