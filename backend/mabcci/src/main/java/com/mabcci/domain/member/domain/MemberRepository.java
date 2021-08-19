package com.mabcci.domain.member.domain;

import com.mabcci.global.common.Email;
import com.mabcci.global.common.Nickname;
import com.mabcci.global.common.Password;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    @Query("select m from Member m join fetch m.memberSpecs where m.nickname = :nickname")
    Optional<Member> findByNickName(@Param("nickname") Nickname nickname);

    @Query("select m from Member m where m.memberRole = :memberRole")
    List<Member> findAllByMemberRole(@Param("memberRole")MemberRole memberRole);

    Optional<Member> findByNicknameAndPassword(Nickname nickname, Password password);

    Optional<Member> findByEmailAndPassword(Email email, Password password);

    @Query(value = "SELECT * FROM member m WHERE m.member_nickname LIKE %:nickname%", nativeQuery = true)
    List<Member> findByNicknameContains(@Param("nickname") String nickname);

    @Query("SELECT m from Member m WHERE m.isPopularMabcci = true")
    List<Member> findAllByIsPopularMabcci();
}
