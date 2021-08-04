package com.mabcci.domain.follow.application;

import com.mabcci.domain.follow.domain.FollowRepository;
import org.springframework.stereotype.Service;

@Service
public class FollowService {

    private final FollowRepository followRepository;

    public FollowService(final FollowRepository followRepository) {
        this.followRepository = followRepository;
    }

}
