package com.mabcci.domain.ootdcomment.ui;

import com.mabcci.domain.ootdcomment.application.OotdCommentDeleteService;
import com.mabcci.domain.ootdcomment.application.OotdCommentSaveService;
import com.mabcci.domain.ootdcomment.application.OotdCommentUpdateService;
import com.mabcci.domain.ootdcomment.dto.request.OotdCommentSaveRequest;
import com.mabcci.domain.ootdcomment.dto.request.OotdCommentUpdateRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

@CrossOrigin(originPatterns = "http://localhost:*")
@RestController
public class OotdCommentController {

    private final OotdCommentSaveService ootdCommentSaveService;
    private final OotdCommentUpdateService ootdCommentUpdateService;
    private final OotdCommentDeleteService ootdCommentDeleteService;

    public OotdCommentController(final OotdCommentSaveService ootdCommentSaveService,
                                 final OotdCommentUpdateService ootdCommentUpdateService,
                                 final OotdCommentDeleteService ootdCommentDeleteService) {
        this.ootdCommentSaveService = ootdCommentSaveService;
        this.ootdCommentUpdateService = ootdCommentUpdateService;
        this.ootdCommentDeleteService = ootdCommentDeleteService;
    }

    @PostMapping("/api/ootd/comments")
    public ResponseEntity saveOotdComment(@Valid @RequestBody final OotdCommentSaveRequest ootdCommentSaveRequest) {
        ootdCommentSaveService.saveOotdComment(ootdCommentSaveRequest);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/api/ootd/comments/{id}")
    public ResponseEntity updateOotdComment(@Positive @PathVariable("id") final Long id,
                                            @Valid @RequestBody final OotdCommentUpdateRequest ootdCommentUpdateRequest) {
        ootdCommentUpdateService.updateOotdComment(id, ootdCommentUpdateRequest);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/api/ootd/comments/{id}")
    public ResponseEntity deleteOotdComment(@Positive @PathVariable("id") final Long id) {
        ootdCommentDeleteService.deleteOotdComment(id);
        return ResponseEntity.noContent().build();
    }
}
