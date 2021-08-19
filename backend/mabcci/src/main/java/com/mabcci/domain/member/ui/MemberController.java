package com.mabcci.domain.member.ui;

import com.mabcci.domain.member.application.MemberDeleteService;
import com.mabcci.domain.member.application.MemberFindService;
import com.mabcci.domain.member.application.MemberJoinService;
import com.mabcci.domain.member.application.MemberUpdateService;
import com.mabcci.domain.member.domain.Member;
import com.mabcci.domain.member.dto.request.MemberDeleteRequest;
import com.mabcci.domain.member.dto.request.MemberJoinRequest;
import com.mabcci.domain.member.dto.request.MemberUpdateRequest;
import com.mabcci.domain.member.dto.response.*;
import com.mabcci.global.common.Nickname;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static com.mabcci.domain.member.domain.MemberRole.MABCCI;

@CrossOrigin(originPatterns = "http://localhost:*")
@RestController
public class MemberController {

    private final Logger log = LoggerFactory.getLogger(getClass());

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
    public ResponseEntity<MemberFindByNickNameResponse> join(@Valid @RequestBody final MemberJoinRequest request) {
        final Member member = memberJoinService.join(request.member(), request.categories());
        final MemberFindByNickNameResponse response = MemberFindByNickNameResponse.ofMember(member);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/api/members/mypage/{nickname}")
    public ResponseEntity<MemberMyPageResponse> findMemberInfoByNickName(@Valid @PathVariable final Nickname nickname) {
        final Member member = memberFindService.findByNickname(nickname);
        final MemberMyPageResponse response = MemberMyPageResponse.ofMember(member);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/api/members/check/{nickname}")
    public ResponseEntity<Boolean> isExistNickname(@Valid @PathVariable final Nickname nickname) {
        boolean existNickname = memberFindService.isExistNickname(nickname);
        return ResponseEntity.ok().body(existNickname);
    }

    @GetMapping("/api/members/{nickname}")
    public ResponseEntity<MemberFindByNickNameResponse> findMemberByNickname(@Valid @PathVariable final Nickname nickname) {
        final Member member = memberFindService.findByNickname(nickname);
        final MemberFindByNickNameResponse response = MemberFindByNickNameResponse.ofMember(member);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/api/members/search")
    public ResponseEntity<MemberFindSimpleResponses> findMemberByNicknameContains(@Valid @RequestParam final Nickname nickname) {
        return ResponseEntity.ok().body(memberFindService.findByNicknameContains(nickname));
    }

    @GetMapping("/api/members")
    public ResponseEntity<List<MemberListResponse>> findAllMember() {
        final List<MemberListResponse> members = memberFindService.findAll();
        return ResponseEntity.ok().body(members);
    }

    @GetMapping("/api/members/mabcci")
    public ResponseEntity<MemberFindMabcciResponses> findMabcci() {
        final List<Member> member = memberFindService.findByMemberRole(MABCCI);
        final List<MemberFindMabcciResponse> responses = member.stream()
                .map(MemberFindMabcciResponse::ofMember)
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(new MemberFindMabcciResponses(responses));
    }

    @PostMapping("/api/members/update/{originalNickname}")
    public ResponseEntity<?> update(@Valid @ModelAttribute MemberUpdateRequest request,
                                    @PathVariable("originalNickname") Nickname originalNickname,
                                    @RequestParam("picture") MultipartFile picture) {
        log.info("requset : {}", request.toString());
        log.info("originalNickname : {}", originalNickname.toString());
        log.info("multipart : {}", picture.toString());
        memberUpdateService.update(
                originalNickname, request.gender(), request.description(), request.nickname(),
                request.height(), request.weight(), request.footSize(), request.bodytype(),
                request.categories(), picture);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/api/members/{nickname}")
    public ResponseEntity<?> delete(@Valid @RequestBody final MemberDeleteRequest request) {
        memberDeleteService.delete(request.nickname(), request.password());
        return ResponseEntity.ok().build();
    }

}
