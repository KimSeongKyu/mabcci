package com.mabcci.domain.ootdLike.domain;

import com.mabcci.domain.ootd.domain.Ootd;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OotdLikeRepository extends JpaRepository<OotdLike, Long> {
    Long countByOotd(Ootd ootd);
}
