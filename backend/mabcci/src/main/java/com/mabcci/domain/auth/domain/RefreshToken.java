package com.mabcci.domain.auth.domain;

import com.mabcci.domain.member.domain.Member;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@NoArgsConstructor
public class RefreshToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "REFRESH_TOKEN_ID")
    private Long id;

    @NotBlank
    @Column(name = "REFRESH_TOKEN", nullable = false, unique = true)
    private String refreshToken;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_EMAIL", referencedColumnName = "MEMBER_EMAIL", nullable = false, unique = true)
    private Member member;

    @Builder
    public RefreshToken(final Member member, final String refreshToken) {
        this.member = member;
        this.refreshToken = refreshToken;
    }
}
