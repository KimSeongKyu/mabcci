package com.mabcci.domain.auth.domain;

import com.mabcci.global.common.Email;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Email> {
}
