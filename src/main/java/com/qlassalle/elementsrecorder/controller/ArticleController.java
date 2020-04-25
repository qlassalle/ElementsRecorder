package com.qlassalle.elementsrecorder.controller;

import com.qlassalle.elementsrecorder.model.Article;
import com.qlassalle.elementsrecorder.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/article")
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    @GetMapping("/")
    public List<Article> findAll() {
        return articleService.findAll();
    }

    @GetMapping("/{id}")
    public Article get(@PathVariable Long id) {
        return articleService.findById(id);
    }

    @PostMapping("/")
    public Article create(@RequestBody Article article) {
        return articleService.create(article);
    }

    @PutMapping("/")
    public Article update(@RequestBody Article article) {
        return articleService.update(article);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        articleService.delete(id);
    }
}
