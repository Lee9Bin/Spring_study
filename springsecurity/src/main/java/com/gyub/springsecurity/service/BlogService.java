package com.gyub.springsecurity.service;

import com.gyub.springsecurity.domain.Article;
import com.gyub.springsecurity.dto.AddArticleRequest;
import com.gyub.springsecurity.dto.UpdateArticleRequest;
import com.gyub.springsecurity.repository.BlogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BlogService {
    private final BlogRepository blogRepository;

    public Article save(AddArticleRequest request) {
        Article article = request.toEntity();
        blogRepository.save(request.toEntity());
        return article;
    }

    public List<Article> findAll() {
        return blogRepository.findAll();
    }

    public Article findById(long id) {
        return blogRepository.findById(id);
    }

    public void delete(long id) {
        blogRepository.deleteById(id);
    }

    @Transactional
    public Article update(long id, UpdateArticleRequest request) {
        Article article = blogRepository.findById(id);
        article.update(request.getTitle(), request.getContent());
        return article;
    }
}