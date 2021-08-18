package com.mabcci.domain.proposalreview.application;

import com.mabcci.domain.proposalreview.domain.ProposalReview;
import com.mabcci.domain.proposalreview.domain.ProposalReviewRepository;
import com.mabcci.domain.proposalreview.dto.response.ProposalReviewFindResponse;
import com.mabcci.domain.proposalreview.dto.response.ProposalReviewFindResponses;
import com.mabcci.global.common.Nickname;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Transactional(readOnly = true)
@Service
public class ProposalReviewFindService {

    private final static int LATELY_THREE_PROPOSAL_REVIEWS_PAGE = 0;
    private final static int LATELY_THREE_PROPOSAL_REVIEWS_SIZE = 3;

    private final ProposalReviewRepository proposalReviewRepository;

    public ProposalReviewFindService(final ProposalReviewRepository proposalReviewRepository) {
        this.proposalReviewRepository = proposalReviewRepository;
    }

    public ProposalReviewFindResponse findProposalReviewByProposalId(final Long id) {
        return ProposalReviewFindResponse.ofProposalReview(getProposalReviewByProposalId(id));
    }

    private ProposalReview getProposalReviewByProposalId(final Long id) {
        return proposalReviewRepository.findByProposalId(id)
                .orElseThrow(IllegalArgumentException::new);
    }

    public ProposalReviewFindResponses findLatelyThreeProposalReviewsByNickname(final Nickname nickname) {
        return new ProposalReviewFindResponses(getListOfProposalReviewFindResponse(nickname));
    }

    private List<ProposalReviewFindResponse> getListOfProposalReviewFindResponse(final Nickname nickname) {
        return mapProposalsReviewsToFindResponses(getLatelyThreeProposalReviews(nickname));
    }

    private List<ProposalReview> getLatelyThreeProposalReviews(final Nickname nickname) {
        return proposalReviewRepository.findLatelyThreeByNickname(nickname,
                PageRequest.of(LATELY_THREE_PROPOSAL_REVIEWS_PAGE, LATELY_THREE_PROPOSAL_REVIEWS_SIZE));
    }

    private List<ProposalReviewFindResponse> mapProposalsReviewsToFindResponses(final List<ProposalReview> proposalReviews) {
        return proposalReviews.stream()
                .map(ProposalReviewFindResponse::ofProposalReview)
                .collect(Collectors.toList());
    }
}
