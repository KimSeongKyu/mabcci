package com.mabcci.domain.auth.common;

import com.mabcci.domain.auth.domain.vo.ClaimType;
import com.mabcci.domain.auth.domain.vo.JwtToken;
import com.mabcci.domain.auth.domain.vo.JwtTokenType;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.security.Key;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Stream;

import static com.mabcci.domain.member.domain.MemberTest.MEMBER;
import static org.assertj.core.api.Assertions.assertThat;

public class JwtUtilTest {

    public static Stream<Arguments> provideClaimTypesForCreateClaimTest() {
        return Stream.of(
                Arguments.of(ClaimType.HEADER, new String[]{"typ", "alg"}),
                Arguments.of(ClaimType.PAYLOAD, new String[]{"iss", "sub", "aud"})
        );
    }

    public static Stream<Arguments> provideTokenTypesForTestsAboutToken() {
        return Arrays.stream(JwtTokenType.values())
                .map(tokenType -> Arguments.of(tokenType));
    }

    @DisplayName(value = "생성 테스트")
    @Test
    public void constructTest() {
        final JwtUtil jwtUtil = new JwtUtil();
        assertThat(jwtUtil).isNotNull();
    }

    @DisplayName(value = "Claim 생성 테스트")
    @ParameterizedTest(name = "{index}. Claim Type: {0} | keys: {1}")
    @MethodSource(value = "provideClaimTypesForCreateClaimTest")
    public void createClaimTest(ClaimType claimType, String[] expectedKeys) {
        final JwtUtil jwtUtil = new JwtUtil();
        final Map<String, Object> claim = jwtUtil.createClaim(claimType);

        assertThat(claim.keySet()).contains(expectedKeys);
    }

    @DisplayName(value = "Payload 생성 테스트")
    @ParameterizedTest(name = "{index}. Token Type: {0}")
    @MethodSource(value = "provideTokenTypesForTestsAboutToken")
    public void createPayloadTest(JwtTokenType jwtTokenType) {
        final JwtUtil jwtUtil = new JwtUtil();
        final String[] expectedKeys = new String[]{"iss", "sub", "aud", "exp", "nbf", "iat", "email", "nickname", "role"};
        final Map<String, Object> payload = jwtUtil.createPayload(jwtTokenType, MEMBER);

        assertThat(payload.keySet()).contains(expectedKeys);
    }

    @DisplayName(value = "Secret key 생성 테스트")
    @Test
    public void createSecretKeyTest() {
        final JwtUtil jwtUtil = new JwtUtil();
        final Key key = jwtUtil.createSecretKey();

        assertThat(key.getAlgorithm()).isEqualTo(SignatureAlgorithm.HS256.getJcaName());
    }

    @DisplayName(value = "Token 생성 테스트")
    @ParameterizedTest(name = "{index}. Token Type: {0}")
    @MethodSource(value = "provideTokenTypesForTestsAboutToken")
    public void createTokenTest(JwtTokenType jwtTokenType) {
        final JwtUtil jwtUtil = new JwtUtil();
        final JwtToken jwtToken = jwtUtil.createToken(jwtTokenType, MEMBER);

        Arrays.stream(jwtToken.jwtToken().split("."))
                .forEach(jwtTokenSplitByComma -> assertThat(jwtTokenSplitByComma).isBase64());
    }

    @DisplayName(value = "유효한 토큰 검증 테스트")
    @ParameterizedTest(name = "{index}. Token Type: {0}")
    @MethodSource(value = "provideTokenTypesForTestsAboutToken")
    public void isValidTokenTest(JwtTokenType jwtTokenType) {
        final JwtUtil jwtUtil = new JwtUtil();
        final JwtToken jwtToken = jwtUtil.createToken(jwtTokenType, MEMBER);
        final boolean validity = jwtUtil.isValidToken(jwtToken);

        assertThat(validity).isTrue();
    }

    @DisplayName(value = "유효하지 않은 토큰 검증 테스트")
    @Test
    public void isNotValidTokenTest() {
        final JwtUtil jwtUtil = new JwtUtil();
        final JwtToken jwtToken = JwtToken.of("invalid.test.token");
        final boolean validity = jwtUtil.isValidToken(jwtToken);

        assertThat(validity).isFalse();
    }
}
