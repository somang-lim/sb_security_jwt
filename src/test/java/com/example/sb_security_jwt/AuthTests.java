package com.example.sb_security_jwt;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@ActiveProfiles("test")
public class AuthTests {
    @Autowired
    private MockMvc mvc;

    @Test
    @DisplayName("POST /member/login 은 로그인 처리 URL 이다.")
    void t1() throws Exception {
        // When
        ResultActions resultActions = mvc
                .perform(post("/member/login")
                        .content("""
                                {
                                    "username": "user1",
                                    "password": "1234"
                                }
                                """.stripIndent())
                        .contentType(new MediaType(APPLICATION_JSON, UTF_8))
                )
                .andDo(print());

        // Then
        resultActions
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    @DisplayName("POST /member/login 으로 올바른 username과 password 데이터를 넘기면 JWT 키를 발급해준다.")
    void t2() throws Exception {
        // When
        ResultActions resultActions = mvc
                .perform(post("/member/login")
                        .content("""
                                {
                                    "username": "user1",
                                    "password": "1234"
                                }
                                """.stripIndent())
                        .contentType(new MediaType(APPLICATION_JSON, UTF_8))
                )
                .andDo(print());

        resultActions
                .andExpect(status().is2xxSuccessful());

        MvcResult mvcResult = resultActions.andReturn();

        MockHttpServletResponse response = mvcResult.getResponse();

        String authentication = response.getHeader("Authentication");

        assertThat(authentication).isNotEmpty();
    }

    @Test
    @DisplayName("POST /member/login 호출할 때 username이나 password 를 누락하면 400")
    void t3() throws Exception {
        // When
        ResultActions resultActions = mvc
                .perform(post("/member/login")
                        .content("""
                                {
                                    "username": "",
                                    "password": "1234"
                                }
                                """.stripIndent())
                        .contentType(new MediaType(APPLICATION_JSON, UTF_8))
                )
                .andDo(print());

        resultActions
                .andExpect(status().is4xxClientError());

        resultActions = mvc
                .perform(post("/member/login")
                        .content("""
                                {
                                    "username": "user1",
                                    "password": ""
                                }
                                """.stripIndent())
                        .contentType(new MediaType(APPLICATION_JSON, UTF_8))
                )
                .andDo(print());

        resultActions.andExpect(status().is4xxClientError());
    }

    @Test
    @DisplayName("POST /member/login 호출할 때 올바르지 않는 username 이나 password 를 입력하면 400")
    void t4() throws Exception {
        // When
        ResultActions resultActions = mvc
                .perform(
                        post("/member/login")
                                .content("""
                                        {
                                            "username": "user3",
                                            "password": "1234"
                                        }
                                        """.stripIndent())
                                .contentType(new MediaType(APPLICATION_JSON, UTF_8))
                )
                .andDo(print());

        // Then
        resultActions
                .andExpect(status().is4xxClientError());

        resultActions = mvc
                .perform(
                        post("/member/login")
                                .content("""
                                        {
                                            "username": "user1",
                                            "password": "12345"
                                        }
                                        """.stripIndent())
                                .contentType(new MediaType(APPLICATION_JSON, UTF_8))
                )
                .andDo(print());

        // Then
        resultActions
                .andExpect(status().is4xxClientError());
    }
}
