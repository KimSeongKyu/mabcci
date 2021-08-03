package com.mabcci.domain.member.ui;

import com.mabcci.domain.member.application.MemberDeleteService;
import com.mabcci.domain.member.application.MemberFindService;
import com.mabcci.domain.member.application.MemberJoinService;
import com.mabcci.domain.member.application.MemberUpdateService;
import com.mabcci.domain.member.domain.Member;
import com.mabcci.domain.member.dto.*;
import com.mabcci.global.common.Nickname;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3030")
@RestController
public class MemberController {

    private final MemberJoinService memberJoinService;
    private final MemberFindService memberFindService;
    private final MemberUpdateService memberUpdateService;
    private final MemberDeleteService memberDeleteService;

    public MemberController(final MemberJoinService memberJoinService, final MemberFindService memberFindService,
                            final MemberUpdateService memberUpdateService, final MemberDeleteService memberDeleteService) {

        this.memberJoinService = memberJoinService;
        this.memberFindService = memberFindService;
        this.memberUpdateService = memberUpdateService;
        this.memberDeleteService = memberDeleteService;
    }

    @PostMapping(value = "/api/members")
    public ResponseEntity<MemberResponseDto> join(@Valid @RequestBody final MemberJoinRequest memberJoinRequest) {
        final Member member = memberJoinService.join(memberJoinRequest.member(), memberJoinRequest.getCategories());
        final MemberResponseDto memberResponseDto = new MemberResponseDto(member);
        return ResponseEntity.ok().body(memberResponseDto);
    }

    @GetMapping("/api/members/{nickname}")
    public ResponseEntity<MemberResponseDto> findByNickname(@Valid @PathVariable final Nickname nickname) {
        final Member member = memberFindService.findByNickName(nickname);
        final MemberResponseDto memberResponseDto = new MemberResponseDto(member);
        return ResponseEntity.ok().body(memberResponseDto);
    }

    @GetMapping("/api/members")
    public ResponseEntity<List<MemberListResponseDto>> findAll() {
        final List<MemberListResponseDto> members = memberFindService.findAll();
        return ResponseEntity.ok().body(members);
    }

    @PutMapping("/api/members/{nickname}")
    public ResponseEntity<?> update(@Valid @RequestBody final MemberUpdateRequest memberUpdateRequest) {
        final Member updatedMember = memberUpdateService.update(memberUpdateRequest.getNickname(), memberUpdateRequest.getGender());
        final MemberResponseDto memberResponseDto = new MemberResponseDto(updatedMember);
        return ResponseEntity.ok().body(memberResponseDto);
    }

    @DeleteMapping("/api/members/{nickname}")
    public ResponseEntity<?> delete(@Valid @RequestBody final MemberDeleteRequest memberDeleteRequest) {
        memberDeleteService.delete(memberDeleteRequest.getNickname(), memberDeleteRequest.getPassword());
        return ResponseEntity.ok().build();
    }

}
