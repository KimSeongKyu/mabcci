package com.mabcci.domain.proposalreview.ui;

import com.mabcci.domain.proposalreview.application.ProposalReviewSaveService;
import com.mabcci.domain.proposalreview.dto.request.ProposalReviewSaveRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@CrossOrigin(originPatterns = "http://localhost:*")
@RestController
public class ProposalReviewController {

    private final ProposalReviewSaveService proposalReviewSaveService;

    public ProposalReviewController(final ProposalReviewSaveService proposalReviewSaveService) {
        this.proposalReviewSaveService = proposalReviewSaveService;
    }

    @PostMapping("/api/proposal-reviews")
    public ResponseEntity saveProposalReview(@Valid @RequestBody final ProposalReviewSaveRequest proposalReviewSaveRequest) {
        proposalReviewSaveService.saveProposalReview(proposalReviewSaveRequest);
        return ResponseEntity.noContent().build();
    }
}
