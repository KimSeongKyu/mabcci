package com.mabcci.domain.hashtag.ui;

import com.mabcci.domain.hashtag.application.HashtagFindService;
import com.mabcci.domain.hashtag.dto.response.HashtagsFindByNameContainsResponse;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(originPatterns = "http://localhost:*")
@RestController
public class HashtagController {

    private final HashtagFindService hashtagFindService;

    public HashtagController(final HashtagFindService hashtagFindService) {
        this.hashtagFindService = hashtagFindService;
    }

    @GetMapping("/api/hashtags/search")
    public HashtagsFindByNameContainsResponse findHashtagNamesContains(final String hashtag) {
        return hashtagFindService.findByNameContains(hashtag);
    }
}
