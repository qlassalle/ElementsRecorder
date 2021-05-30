package com.qlassalle.elementsrecorder.unittests.usecases.article;

import com.qlassalle.elementsrecorder.domain.model.Article;
import com.qlassalle.elementsrecorder.domain.model.UUIDProvider;
import com.qlassalle.elementsrecorder.domain.model.User;
import com.qlassalle.elementsrecorder.domain.model.input.ArticleCreationRequest;
import com.qlassalle.elementsrecorder.domain.usecases.article.CreateArticleUseCase;
import com.qlassalle.elementsrecorder.unittests.adapters.FixedUUIDProvider;
import com.qlassalle.elementsrecorder.unittests.adapters.InMemoryArticleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.validation.Validation;
import javax.validation.Validator;

import static org.assertj.core.api.Assertions.assertThat;

public class CreateArticleUseCaseTest {

    private final UUIDProvider uuidProvider = new FixedUUIDProvider();
    private Validator validator;
    private CreateArticleUseCase createArticleUseCase;

    @BeforeEach
    void setUp() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
        this.createArticleUseCase = new CreateArticleUseCase(new InMemoryArticleRepository(), uuidProvider);
    }

    @DisplayName("User should be able to create an article")
    @Test
    void shouldCreateArticle() {
        String name = "Java for noobs";
        String description = "Gotta start somewhere";
        int rating = 5;
        String url = "www.noobs.com";
        User user = new User(uuidProvider.generate(), "email@gmail.com", "Azertyuiop0.");
        Article expectedArticle = buildExpectedArticle(name, description, rating, url);

        Article article = createArticleUseCase.create(new ArticleCreationRequest(name, description, rating, url),
                                                      user.getId());

        assertThat(validator.validate(article)).isEmpty();
        assertThat(article).usingRecursiveComparison()
                           .ignoringFields("createdAt", "updatedAt")
                           .isEqualTo(expectedArticle);
        assertThat(article.getCreatedAt()).isNotNull();
        assertThat(article.getUpdatedAt()).isNotNull();
    }

    @DisplayName("Article should not be created with invalid URL")
    @Test
    void shouldNotCreateArticleWithInvalidUrl() {
        var articleCreationRequest = new ArticleCreationRequest("Java 101", "Desc", 1, "nogoodurl.com");
        var validationResult = validator.validate(articleCreationRequest);
        assertThat(validationResult).hasSize(1);
        assertThat(validationResult.iterator().next().getMessage()).isEqualTo("{article.url.invalid}");
    }

    private Article buildExpectedArticle(String name, String description, int rating, String url) {
        return Article.builder()
                      .id(uuidProvider.generate())
                      .name(name)
                      .description(description)
                      .rating(rating)
                      .url(url)
                      .userId(uuidProvider.generate())
                      .build();
    }

}
