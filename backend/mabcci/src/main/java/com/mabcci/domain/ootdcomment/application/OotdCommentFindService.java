package com.mabcci.domain.ootdcomment.application;

import com.mabcci.domain.ootdcomment.domain.OotdComment;
import com.mabcci.domain.ootdcomment.domain.OotdCommentRepository;
import com.mabcci.domain.ootdcomment.dto.response.OotdCommentListFindResponse;
import com.mabcci.domain.ootdcomment.dto.response.OotdCommentFindResponse;
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
    public OotdCommentListFindResponse findOotdComments(final Long id) {
        final List<OotdComment> ootdComments = ootdCommentRepository.findAllByOotdId(id);

        final Map<Optional<OotdComment>, List<OotdComment>> childrenOotdCommentsMappedByParent = ootdComments.stream()
                .collect(groupingBy(OotdComment::parentComment));

        final List<OotdCommentFindResponse> childrenOotdCommentFindRespons =
                getOotdCommentResponses(childrenOotdCommentsMappedByParent, Collections.emptyList(), Optional::isPresent);

        final List<OotdCommentFindResponse> parentOotdCommentFindRespons =
                getOotdCommentResponses(childrenOotdCommentsMappedByParent, childrenOotdCommentFindRespons, Optional::isEmpty);

        return new OotdCommentListFindResponse(parentOotdCommentFindRespons);
    }

    private List<OotdCommentFindResponse> getOotdCommentResponses(final Map<Optional<OotdComment>, List<OotdComment>> childrenOotdCommentsMappedByParent,
                                                                  final List<OotdCommentFindResponse> childrenOotdCommentFindRespons,
                                                                  final Predicate<? super Optional> predicate) {
        return childrenOotdCommentsMappedByParent.keySet()
                .stream()
                .filter(predicate)
                .map(childrenOotdCommentsMappedByParent::get)
                .flatMap(List::stream)
                .map(ootdComment -> OotdCommentFindResponse.ofOotdCommentWithChildren(ootdComment, childrenOotdCommentFindRespons))
                .collect(toList());
    }
}
