package com.mabcci.domain.follow.domain;

import com.mabcci.domain.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FollowRepository extends JpaRepository<Follow, Long> {
    Optional<Follow> findByFollowingAndFollower(Member following, Member follower);
}
