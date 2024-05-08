package com.gyub.springsecurity.repository;

import com.gyub.springsecurity.domain.Article;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BlogRepository {

    void save(Article article);
    List<Article> findAll();
    Article findById(long id);
    void deleteById(long id);
}