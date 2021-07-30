package com.mabcci.domain.auth.domain;

import com.mabcci.domain.model.Email;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Email> {
}
