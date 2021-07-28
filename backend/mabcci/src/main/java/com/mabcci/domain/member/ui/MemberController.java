package com.mabcci.domain.member.ui;

import com.mabcci.domain.member.application.MemberService;
import com.mabcci.domain.member.dto.JoinRequest;
import com.mabcci.domain.member.dto.MemberDeleteRequestDto;
import com.mabcci.domain.member.dto.MemberResponseDto;
import com.mabcci.domain.member.dto.MemberUpdateRequestDto;
import com.mabcci.domain.model.Nickname;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class MemberController {

    private final MemberService memberService;

    public MemberController(final MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping(value = "/api/members")
    public ResponseEntity<?> join(@Valid @RequestBody final JoinRequest joinRequest) {
        final MemberResponseDto joinedResponseDto = memberService.join(joinRequest.entity());
        return ResponseEntity.ok().body(joinedResponseDto);
    }

    @GetMapping("/api/members/{nickname}")
    public ResponseEntity<?> findByNickname(@Valid @PathVariable final Nickname nickname) {
        final MemberResponseDto memberResponseDto = memberService.findByNickName(nickname);
        return ResponseEntity.ok().body(memberResponseDto);
    }

    @GetMapping("/api/members")
    public ResponseEntity<?> findAll() {
        final List<MemberResponseDto> members = memberService.findAll();
        return ResponseEntity.ok().body(members);
    }

    @PutMapping("/api/members/{nickname}")
    public ResponseEntity<?> update(@Valid @RequestBody final MemberUpdateRequestDto updateRequestDto) {
        final MemberResponseDto memberResponseDto = memberService.update(updateRequestDto);
        return ResponseEntity.ok().body(memberResponseDto);
    }

    @DeleteMapping("/api/members/{nickname}")
    public ResponseEntity<?> delete(@Valid @RequestBody final MemberDeleteRequestDto memberDeleteRequestDto) {
        memberService.delete(memberDeleteRequestDto.getNickname(), memberDeleteRequestDto.getPassword());
        return ResponseEntity.ok().build();
    }

}
