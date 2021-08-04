package com.mabcci.domain.follow.ui;

import com.mabcci.domain.follow.application.FollowService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FollowController {

    private final FollowService followService;

    public FollowController(final FollowService followService) {
        this.followService = followService;
    }

}
