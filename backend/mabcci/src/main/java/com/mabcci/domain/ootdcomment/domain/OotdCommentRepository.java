package com.mabcci.domain.ootdcomment.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OotdCommentRepository extends JpaRepository<OotdComment, Long> {

    @Query("SELECT DISTINCT o_c " +
            "FROM OotdComment o_c " +
            "JOIN FETCH o_c.ootd o " +
            "JOIN FETCH o_c.member m " +
            "WHERE o.id = :id")
    List<OotdComment> findAllByOotdId(@Param("id") Long id);
}
