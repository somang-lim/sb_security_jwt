package com.example.sb_security_jwt.app.article.service;

import com.example.sb_security_jwt.app.article.entity.Article;
import com.example.sb_security_jwt.app.article.repository.ArticleRepository;
import com.example.sb_security_jwt.app.member.entity.Member;
import com.example.sb_security_jwt.app.security.entity.MemberContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Optional<Article> findById(Long id) {
        return articleRepository.findById(id);
    }

    public void delete(Article article) {
        articleRepository.delete(article);
    }

    public boolean actorCanDelete(MemberContext memberContext, Article article) {
        return memberContext.getId() == article.getAuthor().getId();
    }
}
