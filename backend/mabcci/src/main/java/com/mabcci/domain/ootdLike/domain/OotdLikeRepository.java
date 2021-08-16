package com.mabcci.domain.ootdLike.domain;

import com.mabcci.global.common.Nickname;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface OotdLikeRepository extends JpaRepository<OotdLike, Long> {

    @Query("SELECT o_l " +
            "FROM OotdLike o_l " +
            "JOIN FETCH o_l.ootd o " +
            "JOIN FETCH o_l.member m " +
            "WHERE o.id = :ootdId AND m.nickname = :nickname")
    Optional<OotdLike> findByOotdAndNickname(@Param("ootdId") Long ootdId, @Param("nickname") Nickname nickname);
}
