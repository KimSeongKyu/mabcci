package com.mabcci.domain.proposal.ui;

import com.mabcci.domain.proposal.application.ProposalSaveService;
import com.mabcci.domain.proposal.dto.request.ProposalSaveRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@CrossOrigin(originPatterns = "http://localhost:*")
@RestController
public class ProposalController {

    private final ProposalSaveService proposalSaveService;

    public ProposalController(final ProposalSaveService proposalSaveService) {
        this.proposalSaveService = proposalSaveService;
    }

    @PostMapping("/api/proposals")
    public ResponseEntity saveProposal(@Valid final ProposalSaveRequest proposalSaveRequest) {
        proposalSaveService.saveProposal(proposalSaveRequest);
        return ResponseEntity.noContent().build();
    }
}

