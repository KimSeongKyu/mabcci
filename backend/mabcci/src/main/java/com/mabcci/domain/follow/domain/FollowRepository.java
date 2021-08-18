package com.mabcci.domain.follow.domain;

import com.mabcci.domain.member.domain.Member;
import org.apache.catalina.LifecycleState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface FollowRepository extends JpaRepository<Follow, Long> {
    Optional<Follow> findByFollowingAndFollower(Member following, Member follower);

    @Query("select f from Follow f join fetch f.follower join fetch f.following where f.follower = :member")
    List<Follow> findByFollowing(Member member);

    @Query("select f from Follow f join fetch f.follower join fetch f.following where f.following = :member")
    List<Follow> findByFollower(Member member);

}
