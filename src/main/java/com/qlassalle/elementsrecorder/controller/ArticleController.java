package com.qlassalle.elementsrecorder.controller;

import com.qlassalle.elementsrecorder.model.Article;
import com.qlassalle.elementsrecorder.model.CustomUserDetails;
import com.qlassalle.elementsrecorder.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/article")
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    @GetMapping("/")
    public List<Article> findAll(@AuthenticationPrincipal CustomUserDetails user) {
        return articleService.findAll(user.getId());
    }

    @GetMapping("/{id}")
    public Article get(@PathVariable Long id, @AuthenticationPrincipal CustomUserDetails user) {
        return articleService.findByIdAndUser(id, user.getId());
    }

    @PostMapping("/")
    public Article create(@RequestBody Article article, @AuthenticationPrincipal CustomUserDetails user) {
        return articleService.create(article, user.getId());
    }

    @PutMapping("/")
    public Article update(@RequestBody Article article, @AuthenticationPrincipal CustomUserDetails user) {
        return articleService.update(article, user.getId());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id, @AuthenticationPrincipal CustomUserDetails user) {
        articleService.delete(id, user.getId());
    }
}
