package com.mabcci.domain.ootdhashtag.domain;

import com.mabcci.domain.ootd.domain.Ootd;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OotdHashtagRepository extends JpaRepository<OotdHashtag, Long> {
    List<OotdHashtag> findByOotd(Ootd ootd);
}
