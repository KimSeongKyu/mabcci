package com.mabcci.domain.proposalreview.ui;

import com.mabcci.domain.proposalreview.application.ProposalReviewFindService;
import com.mabcci.domain.proposalreview.application.ProposalReviewSaveService;
import com.mabcci.domain.proposalreview.dto.request.ProposalReviewSaveRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

@CrossOrigin(originPatterns = "http://localhost:*")
@RestController
public class ProposalReviewController {

    private final ProposalReviewSaveService proposalReviewSaveService;
    private final ProposalReviewFindService proposalReviewFindService;

    public ProposalReviewController(final ProposalReviewSaveService proposalReviewSaveService, final ProposalReviewFindService proposalReviewFindService) {
        this.proposalReviewSaveService = proposalReviewSaveService;
        this.proposalReviewFindService = proposalReviewFindService;
    }

    @PostMapping("/api/proposals/reviews")
    public ResponseEntity saveProposalReview(@Valid @RequestBody final ProposalReviewSaveRequest proposalReviewSaveRequest) {
        proposalReviewSaveService.saveProposalReview(proposalReviewSaveRequest);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/api/proposals/{id}/reviews/details")
    public ResponseEntity findProposalReview(@Positive @PathVariable("id") final Long id) {
        return ResponseEntity.ok(proposalReviewFindService.findProposalReviewByProposalId(id));
    }
}
