package com.mabcci.domain.follow.ui;

import com.mabcci.domain.follow.application.FollowFindService;
import com.mabcci.domain.follow.application.FollowService;
import com.mabcci.domain.follow.application.UnFollowService;
import com.mabcci.domain.follow.dto.FollowRequest;
import com.mabcci.domain.follow.dto.FollowResponse;
import com.mabcci.domain.follow.dto.UnFollowRequest;
import com.mabcci.global.common.Nickname;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(originPatterns = "*")
@RestController
public class FollowController {

    private final FollowFindService followFindService;
    private final FollowService followService;
    private final UnFollowService unFollowService;

    public FollowController(final FollowFindService followFindService,
                            final FollowService followService,
                            final UnFollowService unFollowService) {
        this.followFindService = followFindService;
        this.followService = followService;
        this.unFollowService = unFollowService;
    }

    @GetMapping("/api/{nickname}/follower")
    public ResponseEntity<?> findFollower(@Valid @PathVariable final Nickname nickname) {
        final List<FollowResponse> followResponses = followFindService.myFollower(nickname);
        return ResponseEntity.ok().body(followResponses);
    }

    @GetMapping("/api/{nickname}/following")
    public ResponseEntity<?> findFollowing(@Valid @PathVariable Nickname nickname) {
        final List<FollowResponse> followResponses = followFindService.myFollowing(nickname);
        return ResponseEntity.ok().body(followResponses);
    }

    @PostMapping("/api/follow")
    public ResponseEntity<?> follow(@RequestBody final FollowRequest request) {
        System.out.println(request.follower().nickname());
        final Long followId = followService.follow(request.following(), request.follower());
        return ResponseEntity.ok().body(followId);
    }

    @DeleteMapping(value = "/api/unfollow")
    public ResponseEntity<?> unfollow(@RequestBody final UnFollowRequest request) {
        System.out.println(request.follower().nickname());
        unFollowService.unfollow(request.following(), request.follower());
        return ResponseEntity.ok().build();
    }

}
