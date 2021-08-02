package com.mabcci.domain.member.domain;

import com.mabcci.domain.model.Email;
import com.mabcci.domain.model.Nickname;
import com.mabcci.domain.model.Password;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    @Query("select m from Member m join fetch m.memberSpecs where m.nickname = :nickname")
    Optional<Member> findByNickname(@Param("nickname") Nickname nickname);

    Optional<Member> findByNicknameAndPassword(Nickname nickname, Password password);

    Optional<Member> findByEmailAndPassword(Email email, Password password);
}
