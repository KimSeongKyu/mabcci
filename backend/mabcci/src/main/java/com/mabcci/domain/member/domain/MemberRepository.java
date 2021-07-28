package com.mabcci.domain.member.domain;

import com.mabcci.domain.model.Email;
import com.mabcci.domain.model.Nickname;
import com.mabcci.domain.model.Password;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByNickname(Nickname nickname);
    Optional<Member> findByNicknameAndPassword(Nickname nickname, Password password);
    Optional<Member> findByEmailAndPassword(Email email, Password password);
}
