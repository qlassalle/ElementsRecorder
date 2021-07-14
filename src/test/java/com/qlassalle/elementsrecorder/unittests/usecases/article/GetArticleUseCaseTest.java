package com.qlassalle.elementsrecorder.unittests.usecases.article;

import com.qlassalle.elementsrecorder.domain.exceptions.ResourceNotFoundException;
import com.qlassalle.elementsrecorder.domain.model.Article;
import com.qlassalle.elementsrecorder.domain.model.repository.ArticleRepository;
import com.qlassalle.elementsrecorder.domain.usecases.article.GetArticleUseCase;
import com.qlassalle.elementsrecorder.unittests.adapters.InMemoryArticleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class GetArticleUseCaseTest {

    private ArticleRepository articleRepository;
    private GetArticleUseCase getArticleUseCase;
    private final UUID userId = UUID.fromString("c7a74e7b-1b1e-4075-86b5-1d578d0dce6a");

    @BeforeEach
    void setUp() {
        articleRepository = new InMemoryArticleRepository();
        getArticleUseCase = new GetArticleUseCase(articleRepository);
    }

    @DisplayName("Should return empty list when user has no articles")
    @Test
    void shouldReturnEmptyListWhenUserHasNoArticles() {
        assertThat(getArticleUseCase.findAll(UUID.randomUUID())).isEqualTo(Collections.emptyList());
    }

    @DisplayName("Should return all articles for user")
    @Test
    void shouldReturnAllArticlesForUser() {
        Article article = generateArticle();
        Article article2 = new Article(UUID.randomUUID(), "Hello World", "Hello", 5, "somewhere.com", userId);

        articleRepository.save(article);
        articleRepository.save(article2);

        List<Article> savedArticles = getArticleUseCase.findAll(userId);

        assertThat(savedArticles).isEqualTo(List.of(article, article2));
    }

    @DisplayName("Should return only articles belonging to user")
    @Test
    void shouldReturnOnlyArticlesBelongingToUser() {
        Article article = generateArticle();
        Article article2 = new Article(UUID.randomUUID(), "Hello World", "Hello", 5, "somewhere.com", userId);

        articleRepository.save(article);
        articleRepository.save(article2);

        List<Article> savedArticles = getArticleUseCase.findAll(UUID.fromString("38032c19-1ce0-4f16-acb1-d8a8de08a84d"));

        assertThat(savedArticles).isEqualTo(Collections.emptyList());
    }

    @DisplayName("Should return article by id for user")
    @Test
    void shouldReturnArticleByIdForUser() {
        UUID articleId = UUID.fromString("00000000-0000-0000-0000-000000000001");
        Article article = generateArticle(articleId);
        articleRepository.save(article);

        Article retrievedArticle = getArticleUseCase.findById(articleId, userId);

        assertThat(retrievedArticle).isEqualTo(article);
    }
    
    @DisplayName("Should throw if article is not found")
    @Test
    void shouldThrowIfArticleIsNotFound() {
        Article article = generateArticle();
        articleRepository.save(article);
        UUID articleId = UUID.randomUUID();

        assertThatExceptionOfType(ResourceNotFoundException.class).isThrownBy(() -> articleRepository.findById(articleId, userId))
                                                                  .withMessage("No article found with id " + articleId);
    }

    @DisplayName("Should throw if article doesn't belong to user")
    @Test
    void shouldThrowIfArticleDoesntBelongToUser() {
        UUID articleId = UUID.randomUUID();
        UUID otherUserId = UUID.randomUUID();
        Article article = generateArticle(articleId);
        articleRepository.save(article);

        assertThatExceptionOfType(ResourceNotFoundException.class).isThrownBy(() -> articleRepository.findById(articleId, otherUserId))
                                                                  .withMessage("No article found with id " + articleId);
    }

    private Article generateArticle() {
        return new Article(UUID.randomUUID(), "Hello World", "Hello", 5, "hello.com", userId);
    }

    private Article generateArticle(UUID uuid) {
        return new Article(uuid, "Hello World", "Hello", 5, "hello.com", userId);
    }
}
