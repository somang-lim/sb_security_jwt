package com.example.sb_security_jwt.app.article.controller;

import com.example.sb_security_jwt.app.article.entity.Article;
import com.example.sb_security_jwt.app.article.service.ArticleService;
import com.example.sb_security_jwt.app.base.dto.RsData;
import com.example.sb_security_jwt.app.security.entity.MemberContext;
import com.example.sb_security_jwt.app.util.Util;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/articles")
@RequiredArgsConstructor
public class ArticlesController {
    private final ArticleService articleService;

    @GetMapping("")
    public ResponseEntity<RsData> list() {
        List<Article> articles = articleService.findAll();

        return Util.spring.responseEntityOf(
                RsData.successOf(
                        Util.mapOf(
                                "articles", articles
                        )
                )
        );
    }

    @GetMapping("{id}")
    public ResponseEntity<RsData> detail(@PathVariable Long id) {
        Article article = articleService.findById(id).orElse(null);

        if (article == null) {
            return Util.spring.responseEntityOf(
                    RsData.of(
                    "F-1",
                        "해당 게시물은 존재하지 않습니다."
                    )
            );
        }

        return Util.spring.responseEntityOf(
                RsData.successOf(
                        Util.mapOf(
                            "article", article
                        )
                )
        );
    }

    @DeleteMapping("{id}")
    public ResponseEntity<RsData> delete(@PathVariable Long id, @AuthenticationPrincipal MemberContext memberContext) {
        Article article = articleService.findById(id).orElse(null);

        if (article == null) {
            return Util.spring.responseEntityOf(
                    RsData.of(
                    "F-1",
                         "해당 게시물은 존재하지 않습니다."
                    )
            );
        }

        if (articleService.actorCanDelete(memberContext, article) == false) {
            return Util.spring.responseEntityOf(
                    RsData.of(
                    "F-2",
                         "삭제 권한이 없습니다."
                    )
            );
        }

        articleService.delete(article);

        return Util.spring.responseEntityOf(
                RsData.of(
                "S-1",
                     "해당 게시물이 삭제되었습니다."
                )
        );
    }
}
