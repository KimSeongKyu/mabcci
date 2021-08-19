package com.mabcci.domain.member.application;

import com.mabcci.domain.auth.common.JwtUtil;
import com.mabcci.domain.auth.domain.RefreshToken;
import com.mabcci.domain.auth.domain.RefreshTokenRepository;
import com.mabcci.domain.auth.domain.vo.JwtToken;
import com.mabcci.domain.auth.domain.vo.JwtTokenType;
import com.mabcci.domain.auth.dto.response.LoginResponse;
import com.mabcci.domain.category.domain.Category;
import com.mabcci.domain.category.domain.CategoryRepository;
import com.mabcci.domain.member.domain.Member;
import com.mabcci.domain.member.domain.MemberRepository;
import com.mabcci.domain.member.domain.MemberSpecs;
import com.mabcci.domain.membercategory.domain.MemberCategory;
import com.mabcci.domain.picture.common.PictureUtil;
import com.mabcci.global.common.Email;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
public class MemberJoinService {

    private final MemberRepository memberRepository;
    private final CategoryRepository categoryRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtUtil jwtUtil;

    public MemberJoinService(final MemberRepository memberRepository,
                             final CategoryRepository categoryRepository,
                             final RefreshTokenRepository refreshTokenRepository,
                             final JwtUtil jwtUtil) {
        this.memberRepository = memberRepository;
        this.categoryRepository = categoryRepository;
        this.refreshTokenRepository = refreshTokenRepository;
        this.jwtUtil = jwtUtil;
    }

    @Transactional
    public LoginResponse join(final Member member, final Set<String> categoryNames) {
        final MemberSpecs memberSpecs = MemberSpecs.noContent();
        member.updateMemberSpecs(memberSpecs);
        for (String categoryName : categoryNames) {
            final Category category = getCategoryByCategoryName(categoryName);
            final MemberCategory memberCategory = MemberCategory.fromMemberAndCategory(member, category);
            member.addMemberCategory(memberCategory);
        }
        final Member savedMember = memberRepository.save(member);
        final JwtToken accessToken = jwtUtil.createToken(JwtTokenType.ACCESS_TOKEN, member);
        final JwtToken refreshToken = jwtUtil.createToken(JwtTokenType.REFRESH_TOKEN, member);

        saveRefreshToken(savedMember.email(), refreshToken);
        return new LoginResponse(accessToken, refreshToken);
    }


    private Category getCategoryByCategoryName(final String categoryName) {
        return categoryRepository.findByCategoryName(categoryName)
                .orElseThrow(IllegalArgumentException::new);
    }

    private void saveRefreshToken(final Email email, final JwtToken refreshToken) {
        refreshTokenRepository.save(buildRefreshToken(email, refreshToken));
    }

    private RefreshToken buildRefreshToken(final Email email, final JwtToken refreshToken) {
        return RefreshToken.builder()
                .email(email)
                .refreshToken(refreshToken)
                .build();
    }


}
