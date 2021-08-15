package com.mabcci.domain.ootdLike.domain;

import com.mabcci.domain.ootd.domain.Ootd;
import com.mabcci.global.common.Nickname;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface OotdLikeRepository extends JpaRepository<OotdLike, Long> {

    @Query("SELECT o_l " +
            "FROM OotdLike o_l " +
            "JOIN FETCH o_l.member m " +
            "WHERE o_l.ootd = :ootd AND m.nickname = :nickname")
    Optional<OotdLike> findByOotdAndNickname(@Param("ootd") Ootd ootd, @Param("nickname") Nickname nickname);
}
