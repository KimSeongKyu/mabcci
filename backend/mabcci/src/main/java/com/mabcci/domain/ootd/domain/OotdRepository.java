package com.mabcci.domain.ootd.domain;

import com.mabcci.domain.member.domain.Member;
import com.mabcci.global.common.Nickname;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface OotdRepository extends JpaRepository<Ootd, Long> {

    @Query(value = "SELECT DISTINCT o " +
            "FROM Ootd o " +
            "JOIN FETCH o.member m " +
            "JOIN FETCH m.followings f " +
            "LEFT OUTER JOIN o.ootdLikes o_l " +
            "JOIN o.ootdPictures p " +
            "LEFT OUTER JOIN o.ootdHashtags o_h " +
            "LEFT OUTER JOIN o_h.hashtag h " +
            "WHERE f.follower = :member " +
            "ORDER BY o.id DESC",
            countQuery = "SELECT count(o) " +
                    "FROM Ootd o " +
                    "JOIN o.member m " +
                    "JOIN m.followings f " +
                    "LEFT OUTER JOIN o.ootdLikes o_l " +
                    "JOIN o.ootdPictures p " +
                    "LEFT OUTER JOIN o.ootdHashtags o_h " +
                    "LEFT OUTER JOIN o_h.hashtag h " +
                    "WHERE f.follower = :member " +
                    "ORDER BY o.id DESC")
    Page<Ootd> findOotdsOfFollowing(@Param("member") Member member, Pageable pageable);

    @Query(value = "SELECT DISTINCT o " +
            "FROM Ootd o " +
            "JOIN FETCH o.member m " +
            "LEFT OUTER JOIN o.ootdLikes o_l " +
            "JOIN o.ootdPictures p " +
            "LEFT OUTER JOIN o.ootdHashtags o_h " +
            "LEFT OUTER JOIN o_h.hashtag h " +
            "ORDER BY o.id DESC",
            countQuery = "SELECT count(o) " +
                    "FROM Ootd o " +
                    "JOIN o.member m " +
                    "LEFT OUTER JOIN o.ootdLikes o_l " +
                    "JOIN o.ootdPictures p " +
                    "LEFT OUTER JOIN o.ootdHashtags o_h " +
                    "LEFT OUTER JOIN o_h.hashtag h")
    Page<Ootd> findOotds(Pageable pageable);

    @Query("SELECT DISTINCT o " +
            "FROM Ootd o " +
            "JOIN FETCH o.member m " +
            "LEFT OUTER JOIN o.ootdLikes o_l " +
            "JOIN o.ootdPictures p " +
            "LEFT OUTER JOIN o.ootdHashtags o_h " +
            "LEFT OUTER JOIN o_h.hashtag h " +
            "WHERE o.id = :id")
    Optional<Ootd> findOotdDetailById(@Param("id") Long id);

    @Query("SELECT DISTINCT o " +
            "FROM Ootd o " +
            "JOIN o.ootdHashtags o_h " +
            "JOIN o_h.hashtag h " +
            "WHERE h.name = :hashtagName " +
            "ORDER BY o.id DESC")
    Page<Ootd> findOotdsByHashtagName(@Param("hashtagName") String hashtagName, Pageable pageable);

    @Query(value = "SELECT DISTINCT o " +
            "FROM Ootd o " +
            "JOIN FETCH o.member m " +
            "WHERE m.nickname = :nickname " +
            "ORDER BY o.id DESC",
            countQuery = "SELECT count(o) " +
                    "FROM Ootd o " +
                    "JOIN o.member m " +
                    "WHERE m.nickname = :nickname")
    Page<Ootd> findOotdsByNickname(@Param("nickname") Nickname nickname, Pageable pageable);
}
