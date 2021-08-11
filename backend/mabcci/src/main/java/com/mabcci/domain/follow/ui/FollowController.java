package com.mabcci.domain.follow.ui;

import com.mabcci.domain.follow.application.FollowService;
import com.mabcci.domain.follow.application.UnFollowService;
import com.mabcci.domain.follow.dto.FollowRequest;
import com.mabcci.domain.follow.dto.UnFollowRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FollowController {

    private final FollowService followService;
    private final UnFollowService unFollowService;

    public FollowController(final FollowService followService, final UnFollowService unFollowService) {
        this.followService = followService;
        this.unFollowService = unFollowService;
    }

    @PostMapping("/api/follow")
    public ResponseEntity<?> follow(@RequestBody final FollowRequest request) {
        final Long followId = followService.follow(request.following(), request.follower());
        return ResponseEntity.ok().body(followId);
    }

    @DeleteMapping(value = "/api/unfollow")
    public ResponseEntity<?> unfollow(@RequestBody final UnFollowRequest request) {
        unFollowService.unfollow(request.following(), request.follower());
        return ResponseEntity.ok().build();
    }

}
