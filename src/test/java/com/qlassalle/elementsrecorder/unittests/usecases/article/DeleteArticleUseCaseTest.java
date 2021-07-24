package com.qlassalle.elementsrecorder.unittests.usecases.article;

import com.qlassalle.elementsrecorder.domain.model.Article;
import com.qlassalle.elementsrecorder.domain.model.UUIDProvider;
import com.qlassalle.elementsrecorder.domain.model.repository.ArticleRepository;
import com.qlassalle.elementsrecorder.domain.usecases.article.DeleteArticleUseCase;
import com.qlassalle.elementsrecorder.unittests.adapters.FixedUUIDProvider;
import com.qlassalle.elementsrecorder.unittests.adapters.InMemoryArticleRepository;
import org.junit.jupiter.api.*;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class DeleteArticleUseCaseTest {

    private final UUIDProvider fixedUUIDProvider = new FixedUUIDProvider();
    private DeleteArticleUseCase deleteArticleUseCase;
    private ArticleRepository articleRepository;

    @BeforeEach
    void setUp() {
        articleRepository = new InMemoryArticleRepository();
        deleteArticleUseCase = new DeleteArticleUseCase(articleRepository);
    }

    @DisplayName("Should delete existing article")
    @Test
    void shouldDeleteExistingArticle() {
        UUID articleId = fixedUUIDProvider.generate();
        UUID userId = fixedUUIDProvider.generate();
        saveArticle(articleId, userId);

        deleteArticleUseCase.delete(articleId, userId);

        assertThat(articleRepository.findAll(userId)).isEqualTo(List.of());
    }

    @DisplayName("Should do nothing if article does not exist")
    @Test
    void shouldDoNothingIfArticleDoesNotExist() {
        UUID userId = fixedUUIDProvider.generate();
        saveArticle(fixedUUIDProvider.generate(), userId);

        deleteArticleUseCase.delete(UUID.randomUUID(), userId);

        assertThat(articleRepository.findAll(userId).size()).isEqualTo(1);
    }

    @DisplayName("Should do nothing if user doesn't own resource")
    @Test
    void shouldDoNothingIfUserDoesNotOwnResource() {
        UUID articleId = fixedUUIDProvider.generate();
        UUID userOwnerId = fixedUUIDProvider.generate();
        saveArticle(articleId, userOwnerId);

        deleteArticleUseCase.delete(articleId, UUID.randomUUID());

        assertThat(articleRepository.findAll(userOwnerId).size()).isEqualTo(1);
    }

    private void saveArticle(UUID articleId, UUID userId) {
        Article existingArticle = Article.builder()
                                         .id(articleId)
                                         .userId(userId)
                                         .build();

        articleRepository.save(existingArticle);
    }
}
