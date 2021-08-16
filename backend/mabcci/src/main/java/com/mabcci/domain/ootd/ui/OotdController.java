package com.mabcci.domain.ootd.ui;

import com.mabcci.domain.ootd.application.OotdDeleteService;
import com.mabcci.domain.ootd.application.OotdFindService;
import com.mabcci.domain.ootd.application.OotdSaveService;
import com.mabcci.domain.ootd.application.OotdUpdateService;
import com.mabcci.domain.ootd.domain.OotdFilter;
import com.mabcci.domain.ootd.dto.request.OotdUpdateRequest;
import com.mabcci.domain.ootd.dto.request.OotdWithPicturesAndHashtagsRegisterRequest;
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

    private final OotdSaveService ootdSaveService;
    private final OotdFindService ootdFindService;
    private final OotdUpdateService ootdUpdateService;
    private final OotdDeleteService ootdDeleteService;

    public OotdController(final OotdSaveService ootdSaveService, final OotdFindService ootdFindService,
                          final OotdUpdateService ootdUpdateService, final OotdDeleteService ootdDeleteService) {
        this.ootdSaveService = ootdSaveService;
        this.ootdFindService = ootdFindService;
        this.ootdUpdateService = ootdUpdateService;
        this.ootdDeleteService = ootdDeleteService;
    }

    @PostMapping("/api/ootds")
    public ResponseEntity registerOotdWithPicturesAndHashtags(@Valid final OotdWithPicturesAndHashtagsRegisterRequest request) {
        ootdSaveService.saveOotdAndPicturesAndHashtags(request);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/api/ootds/{id}/detail/{nickname}")
    public ResponseEntity findOotdDetail(@NotNull @Positive @PathVariable("id") final Long id,
                                         @Valid @NotNull @PathVariable("nickname") final Nickname nickname) {
        return ResponseEntity.ok(ootdFindService.findOotdDetail(id, nickname));
    }

    @GetMapping("/api/ootds/{nickname}")
    public ResponseEntity findFilteredOotdList(@NotBlank @PathVariable("nickname") final Nickname nickname,
                                               @NotBlank @RequestParam("filter") final OotdFilter ootdFilter,
                                               @NotNull final Pageable pageable) {
        return ResponseEntity.ok(ootdFindService.findOotds(nickname, ootdFilter, pageable));
    }

    @PutMapping("/api/ootds/{id}")
    public ResponseEntity updateOotd(@Positive @PathVariable("id") final Long id,
                                     @Valid @NotNull @RequestBody final OotdUpdateRequest ootdUpdateRequest) {
        ootdUpdateService.updateOotd(id, ootdUpdateRequest);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/api/ootds/{id}")
    public ResponseEntity deleteOotd(@Positive @PathVariable("id") final Long id) {
        ootdDeleteService.deleteOotdById(id);
        return ResponseEntity.noContent().build();
    }
}
