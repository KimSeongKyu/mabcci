package com.mabcci.domain.proposal.ui;

import com.mabcci.domain.proposal.application.ProposalDeleteService;
import com.mabcci.domain.proposal.application.ProposalFindService;
import com.mabcci.domain.proposal.application.ProposalSaveService;
import com.mabcci.domain.proposal.domain.ProposalFilter;
import com.mabcci.domain.proposal.dto.request.ProposalSaveRequest;
import com.mabcci.global.common.Nickname;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

@CrossOrigin(originPatterns = "http://localhost:*")
@RestController
public class ProposalController {

    private final ProposalSaveService proposalSaveService;
    private final ProposalFindService proposalFindService;
    private final ProposalDeleteService proposalDeleteService;

    public ProposalController(final ProposalSaveService proposalSaveService, final ProposalFindService proposalFindService,
                              final ProposalDeleteService proposalDeleteService) {
        this.proposalSaveService = proposalSaveService;
        this.proposalFindService = proposalFindService;
        this.proposalDeleteService = proposalDeleteService;
    }

    @PostMapping("/api/proposals")
    public ResponseEntity saveProposal(@Valid final ProposalSaveRequest proposalSaveRequest) {
        System.out.println(proposalSaveRequest);
        proposalSaveService.saveProposal(proposalSaveRequest);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/api/proposals")
    public ResponseEntity findProposals(@RequestParam("filter") final ProposalFilter proposalFilter,
                                        @Valid @RequestParam("nickname") final Nickname nickname) {
        return ResponseEntity.ok(proposalFindService.findProposals(proposalFilter, nickname));
    }

    @GetMapping("/api/proposals/{id}")
    public ResponseEntity findProposal(@Positive @PathVariable("id") final Long id) {
        return ResponseEntity.ok(proposalFindService.findProposal(id));
    }

    @DeleteMapping("/api/proposals/{id}")
    public ResponseEntity deleteProposal(@Positive @PathVariable("id") final Long id) {
        proposalDeleteService.deleteProposalById(id);
        return ResponseEntity.noContent().build();
    }
}

