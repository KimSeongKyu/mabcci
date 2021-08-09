package com.mabcci.domain.ootd.ui;

import com.mabcci.domain.ootd.application.OotdService;
import com.mabcci.domain.ootd.domain.OotdFilter;
import com.mabcci.domain.ootd.dto.OotdUpdateRequest;
import com.mabcci.domain.ootd.dto.OotdWithPicturesAndHashtagsRegisterRequest;
import com.mabcci.global.common.Nickname;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@CrossOrigin(originPatterns = "http://localhost:*")
@RestController
public class OotdController {

    private final OotdService ootdService;

    public OotdController(final OotdService ootdService) {
        this.ootdService = ootdService;
    }

    @PostMapping("/api/ootds")
    public ResponseEntity registerOotdWithPicturesAndHashtags(@Valid final OotdWithPicturesAndHashtagsRegisterRequest request) {
        ootdService.saveOotdAndPicturesAndHashtags(request);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/api/ootds/{nickname}")
    public ResponseEntity findFilteredOotdList(@NotBlank @PathVariable("nickname") final Nickname nickname,
                                               @NotBlank @RequestParam("filter") final OotdFilter ootdFilter,
                                               @NotNull final Pageable pageable) {
        return ResponseEntity.ok(ootdService.findFilteredOotdList(nickname, ootdFilter, pageable));
    }

    @PutMapping("/api/ootds/{id}")
    public ResponseEntity updateOotd(@Positive @PathVariable("id") final Long id,
                                     @Valid @NotNull @RequestBody final OotdUpdateRequest ootdUpdateRequest) {
        ootdService.updateOotd(id, ootdUpdateRequest);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/api/ootds/{id}")
    public ResponseEntity deleteOotd(@Positive @PathVariable("id") final Long id) {
        ootdService.deleteOotd(id);
        return ResponseEntity.noContent().build();
    }
}
