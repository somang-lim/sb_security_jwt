package com.example.sb_security_jwt.app.article.entity;

import com.example.sb_security_jwt.app.base.entity.BaseEntity;
import com.example.sb_security_jwt.app.member.entity.Member;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@ToString(callSuper = true)
public class Article extends BaseEntity {
    @ManyToOne
    private Member author;
    @Column(unique = true)
    private String subject;
    @JsonIgnore
    private String content;

    public Article(long id) {
        super(id);
    }
}
