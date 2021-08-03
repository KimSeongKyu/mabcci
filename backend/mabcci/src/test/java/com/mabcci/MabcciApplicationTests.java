package com.mabcci;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MabcciApplicationTests {

    @Test
    void contextLoads() {
        MabcciApplication.main(new String[] {});
    }

}
