package com.mabcci.domain.follow.ui;

import com.mabcci.domain.follow.application.FollowService;
import com.mabcci.domain.follow.dto.FollowSaveRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FollowController {

    private final FollowService followService;

    public FollowController(final FollowService followService) {
        this.followService = followService;
    }

    @PostMapping("/api/follow")
    public ResponseEntity<?> save(@RequestBody FollowSaveRequest request) {
        final Long followId = followService.save(request.following(), request.follower());
        return ResponseEntity.ok().body(followId);
    }

}
