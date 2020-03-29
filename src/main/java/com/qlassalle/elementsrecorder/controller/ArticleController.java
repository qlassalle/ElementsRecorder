package com.qlassalle.elementsrecorder.controller;

import com.qlassalle.elementsrecorder.model.Article;
import com.qlassalle.elementsrecorder.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/article")
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    @GetMapping("/")
    public List<Article> getAll() {
        return articleService.getAll();
    }
}
