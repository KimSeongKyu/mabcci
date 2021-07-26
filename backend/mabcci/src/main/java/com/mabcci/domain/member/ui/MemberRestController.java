package com.mabcci.domain.member.ui;

import com.mabcci.domain.member.application.MemberService;
import com.mabcci.domain.member.dto.MemberResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RestController
public class MemberRestController {

    private final MemberService memberService;

    public MemberRestController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/api/members")
    public ResponseEntity<?> findAll() {
        List<MemberResponseDto> members = memberService.findAll();
        validateNull(members);
        return ResponseEntity.ok().body(members);
    }


    private void validateNull(Object object) {
        if(Objects.isNull(object)) {
            throw new AssertionError();
        }
    }

}
