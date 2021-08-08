package com.mabcci.domain.ootdpicture.domain;

import com.mabcci.domain.ootd.domain.Ootd;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OotdPictureRepository extends JpaRepository<OotdPicture, Long> {
    Optional<OotdPicture> findFirstByOotd(Ootd ootd);
}
