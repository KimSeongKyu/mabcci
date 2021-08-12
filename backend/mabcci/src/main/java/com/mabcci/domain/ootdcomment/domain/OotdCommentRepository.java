package com.mabcci.domain.ootdcomment.domain;

import com.mabcci.domain.ootd.domain.Ootd;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OotdCommentRepository extends JpaRepository<OotdComment, Long> {
    List<OotdComment> findAllByOotd(Ootd ootd);
}
