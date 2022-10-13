package com.example.sb_security_jwt;

import com.example.sb_security_jwt.app.member.service.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class CacheTests {
    @Autowired
    private MemberService memberService;

    @Test
    @DisplayName("캐시 사용")
    void t1() throws Exception {
        int rs = memberService.getCacheInt();

        assertThat(rs).isGreaterThan(0);
        System.out.println(rs);

        rs = memberService.getCacheInt();

        assertThat(rs).isGreaterThan(0);
        System.out.println(rs);
    }

    @Test
    @DisplayName("캐시 삭제")
    void t2() throws Exception {
        int rs = memberService.getCacheInt();
        System.out.println(rs);

        rs = memberService.getCacheInt();
        System.out.println(rs);

        memberService.deleteCacheKey1();

        rs = memberService.getCacheInt();
        System.out.println(rs);
    }
}
