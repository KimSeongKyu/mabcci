package com.mabcci.domain.ootdLike.ui;

import com.mabcci.domain.ootdLike.application.OotdLikeSaveAndUpdateService;
import com.mabcci.global.common.Nickname;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@CrossOrigin(originPatterns = "*")
@RestController
public class OotdLikeController {

    private final OotdLikeSaveAndUpdateService ootdLikeSaveAndUpdateService;

    public OotdLikeController(final OotdLikeSaveAndUpdateService ootdLikeSaveAndUpdateService) {
        this.ootdLikeSaveAndUpdateService = ootdLikeSaveAndUpdateService;
    }

    @PostMapping("/api/ootd/{id}/like/{nickname}")
    public ResponseEntity saveOrUpdateOotdLike(@Positive @PathVariable("id") final Long id,
                                               @Valid @NotNull @PathVariable("nickname") final Nickname nickname) {
        ootdLikeSaveAndUpdateService.saveOrUpdateOotdLike(id, nickname);
        return ResponseEntity.noContent().build();
    }
}
