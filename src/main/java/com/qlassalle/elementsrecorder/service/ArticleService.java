package com.qlassalle.elementsrecorder.service;

import com.qlassalle.elementsrecorder.entities.repository.ArticleRepository;
import com.qlassalle.elementsrecorder.model.Article;
import com.qlassalle.elementsrecorder.model.mappers.ArticleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;

    public List<Article> getAll() {
        return articleRepository.findAll().stream().map(ArticleMapper::map).collect(Collectors.toList());
    }
}
