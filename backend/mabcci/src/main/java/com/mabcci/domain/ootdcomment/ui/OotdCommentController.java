package com.mabcci.domain.ootdcomment.ui;

import com.mabcci.domain.ootdcomment.application.OotdCommentSaveService;
import com.mabcci.domain.ootdcomment.dto.request.OotdCommentSaveRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@CrossOrigin(originPatterns = "http://localhost:*")
@RestController
public class OotdCommentController {

    private final OotdCommentSaveService ootdCommentSaveService;

    public OotdCommentController(final OotdCommentSaveService ootdCommentSaveService) {
        this.ootdCommentSaveService = ootdCommentSaveService;
    }

    @PostMapping("/api/ootd/comments")
    public ResponseEntity saveOotdComment(@Valid @RequestBody final OotdCommentSaveRequest ootdCommentSaveRequest) {
        ootdCommentSaveService.saveOotdComment(ootdCommentSaveRequest);
        return ResponseEntity.noContent().build();
    }
}
