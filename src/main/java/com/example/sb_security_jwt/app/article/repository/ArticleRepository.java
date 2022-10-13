package com.example.sb_security_jwt.app.article.repository;

import com.example.sb_security_jwt.app.article.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {
}
