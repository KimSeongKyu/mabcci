package com.mabcci.domain.member.domain;

import com.mabcci.global.common.Email;
import com.mabcci.global.common.Nickname;
import com.mabcci.global.common.Password;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    @Query("select m from Member m join fetch m.memberSpecs where m.nickname = :nickname")
    Optional<Member> findByNicknameWithMemberSpecs(@Param("nickname") Nickname nickname);

    @Query("select m from Member m join fetch m.memberCategories where m.memberRole = :memberRole")
    Optional<Member> findByMemberRoleWithMemberCategory(@Param("memberRole")MemberRole memberRole);

    Optional<Member> findByNicknameAndPassword(Nickname nickname, Password password);

    Optional<Member> findByEmailAndPassword(Email email, Password password);
}
