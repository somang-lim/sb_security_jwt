package com.example.sb_security_jwt;

import com.example.sb_security_jwt.app.util.Util;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class UtilTests {

    @Test
    @DisplayName("Util.mapOf")
    void t1() throws Exception {
        Map<String, Integer> ages = Util.mapOf("마크", 24, "해찬", 23, "제노", 23, "지성", 21);

        assertThat(ages.get("마크")).isEqualTo(24);
        assertThat(ages.get("해찬")).isEqualTo(23);
        assertThat(ages.get("제노")).isEqualTo(23);
        assertThat(ages.get("지성")).isEqualTo(21);
    }
}
