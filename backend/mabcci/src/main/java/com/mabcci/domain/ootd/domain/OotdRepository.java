package com.mabcci.domain.ootd.domain;

import com.mabcci.domain.member.domain.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OotdRepository extends JpaRepository<Ootd, Long> {

    @Query(
    value = "SELECT DISTINCT o " +
            "FROM Ootd o " +
            "JOIN FETCH o.member m " +
            "JOIN FETCH m.followings f " +
            "LEFT OUTER JOIN o.ootdLikes o_l " +
            "JOIN o.ootdPictures p " +
            "LEFT OUTER JOIN o.ootdHashtags o_h " +
            "LEFT OUTER JOIN o_h.hashtag h " +
            "WHERE f.follower = :member",
    countQuery = "SELECT count(o) " +
            "FROM Ootd o " +
            "JOIN o.member m " +
            "JOIN m.followings f " +
            "LEFT OUTER JOIN o.ootdLikes o_l " +
            "JOIN o.ootdPictures p " +
            "LEFT OUTER JOIN o.ootdHashtags o_h " +
            "LEFT OUTER JOIN o_h.hashtag h " +
            "WHERE f.follower = :member")
    Page<Ootd> findOotdsOfFollowing(@Param("member") Member member, Pageable pageable);

    @Query(value = "SELECT DISTINCT o " +
            "FROM Ootd o " +
            "JOIN FETCH o.member m " +
            "LEFT OUTER JOIN o.ootdLikes o_l " +
            "JOIN o.ootdPictures p " +
            "LEFT OUTER JOIN o.ootdHashtags o_h " +
            "LEFT OUTER JOIN o_h.hashtag h",
            countQuery = "SELECT count(o) " +
                    "FROM Ootd o " +
                    "JOIN o.member m " +
                    "LEFT OUTER JOIN o.ootdLikes o_l " +
                    "JOIN o.ootdPictures p " +
                    "LEFT OUTER JOIN o.ootdHashtags o_h " +
                    "LEFT OUTER JOIN o_h.hashtag h")
    Page<Ootd> findOotds(Pageable pageable);
}
