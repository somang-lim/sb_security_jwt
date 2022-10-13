package com.example.sb_security_jwt.app.article.service;

import com.example.sb_security_jwt.app.article.entity.Article;
import com.example.sb_security_jwt.app.article.repository.ArticleRepository;
import com.example.sb_security_jwt.app.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;

    public Article write(Member author, String subject, String content) {
        Article article = Article.builder()
                .author(author)
                .subject(subject)
                .content(content)
                .build();

        articleRepository.save(article);

        return article;
    }

    public List<Article> findAll() {
        return articleRepository.findAllByOrderByIdDesc();
    }
}
