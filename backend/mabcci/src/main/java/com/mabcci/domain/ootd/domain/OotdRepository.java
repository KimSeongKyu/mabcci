package com.mabcci.domain.ootd.domain;

import com.mabcci.domain.member.domain.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OotdRepository extends JpaRepository<Ootd, Long> {

    @Query("select o from Ootd o join o.member m join m.followings f where f.follower = :member")
    Page<Ootd> findAllOfFollowing(@Param("member") Member member, Pageable pageable);
}
