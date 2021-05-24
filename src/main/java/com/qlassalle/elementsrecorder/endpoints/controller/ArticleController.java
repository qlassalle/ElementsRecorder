package com.qlassalle.elementsrecorder.endpoints.controller;

import com.qlassalle.elementsrecorder.domain.model.Article;
import com.qlassalle.elementsrecorder.domain.model.CustomUserDetails;
import com.qlassalle.elementsrecorder.domain.model.input.ArticleCreationRequest;
import com.qlassalle.elementsrecorder.endpoints.models.ArticleDTO;
import com.qlassalle.elementsrecorder.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/article")
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    @GetMapping("/")
    public List<Article> findAll(@AuthenticationPrincipal CustomUserDetails user) {
        return articleService.findAll(user.getUser());
    }

    @GetMapping("/{id}")
    public Article get(@PathVariable Long id, @AuthenticationPrincipal CustomUserDetails user) {
        return articleService.findById(id, user.getUser());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ArticleDTO create(@RequestBody @Valid ArticleCreationRequest articleCreationRequest,
                             @AuthenticationPrincipal CustomUserDetails user) {
        Article article = articleService.create(articleCreationRequest, user.getUser());
        return new ArticleDTO(article);
    }

//    @PutMapping("/{id}")
//    public Article update(@PathVariable Long id, @RequestBody Article article,
//                          @AuthenticationPrincipal CustomUserDetails user) {
//        return articleService.update(id, article, user.getUser());
//    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id, @AuthenticationPrincipal CustomUserDetails user) {
        articleService.delete(id, user.getUser());
    }
}
