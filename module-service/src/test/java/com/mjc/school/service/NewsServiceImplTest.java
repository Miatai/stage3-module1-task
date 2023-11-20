package com.mjc.school.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.mjc.school.repository.impl.NewsRepositoryImpl;
import com.mjc.school.repository.interfaces.Repository;
import com.mjc.school.repository.model.NewsModel;
import com.mjc.school.service.dto.NewsDTORequest;
import com.mjc.school.service.dto.NewsDTOResponse;
import com.mjc.school.service.exception.NotFoundException;
import com.mjc.school.service.exception.ValidatorException;
import com.mjc.school.service.impl.NewsServiceImpl;
import com.mjc.school.service.interfaces.Service;
import com.mjc.school.service.mapper.NewsMapper;
import com.mjc.school.service.mapper.NewsMapperImpl;

public class NewsServiceImplTest {
    private Service<NewsDTOResponse, NewsDTORequest> newsService;
    private Repository<NewsModel> newsRepository;
    private List<NewsModel> newsList;
    private NewsMapper mapper = new NewsMapperImpl();

    @BeforeEach
    public void init() {
        newsService = new NewsServiceImpl();
        newsRepository = new NewsRepositoryImpl();
        newsList = newsRepository.getAll();
    }

    @DisplayName("JUnit test for getAll method")
    @Test
    public void shouldReturnListOfAllNews() {
        List<NewsDTOResponse> result = newsService.getAll();
        assertEquals(newsList.size(), result.size());
    }

    @DisplayName("JUnit test for getById method")
    @Test
    public void shouldReturnNewsDTOResponceWithGivenId() {
        Long id = 2L;
        NewsDTOResponse expected = mapper.convertToDTO(newsList.get((int) (id - 1)));
        assertEquals(expected, newsService.getById(id));
    }

    @DisplayName("JUnit test for getById method. News with such id does not exist.")
    @Test
    public void shouldThrowNotFoundExceptionWhenNewsIdDoesNotExistForGet() {
        Long id = 58L;
        assertThrows(NotFoundException.class, () -> newsService.getById(id));
    }

    @DisplayName("JUnit test for getById method. Id lesser than allowed.")
    @Test
    public void shouldThrowValidatorExceptionWhenNewsIdLesserThanAllowed() {
        Long id = 0L;
        assertThrows(ValidatorException.class, () -> newsService.getById(id));
    }

    @DisplayName("JUnit test for create method.")
    @Test
    public void shouldReturnCreatedNewsDTO() {
        NewsDTORequest entity = new NewsDTORequest(null, "testTitle", "testContent", 15L);
        NewsDTOResponse expected = new NewsDTOResponse(null, "testTitle", "testContent", null, null, 15L);
        NewsDTOResponse result = newsService.create(entity);
        assertEquals(expected.title(), result.title());
        assertEquals(expected.content(), result.content());
        assertEquals(expected.authorId(), result.authorId());
    }

    @DisplayName("JUnit test for create method. Invalid Title.")
    @Test
    public void shouldThrowValidatorExceptionOnInvalidTitle() {
        NewsDTORequest entity = new NewsDTORequest(null, "test", "testContent", 15L);
        assertThrows(ValidatorException.class, () -> newsService.create(entity));
    }

    @DisplayName("JUnit test for create method. Invalid content.")
    @Test
    public void shouldThrowValidatorExceptionOnInvalidContent() {
        NewsDTORequest entity = new NewsDTORequest(null, "testTitle", "test", 15L);
        assertThrows(ValidatorException.class, () -> newsService.create(entity));
    }

    @DisplayName("JUnit test for create method. Invalid author id.")
    @Test
    public void shouldThrowValidatorExceptionOnInvalidAuthorId() {
        NewsDTORequest entity = new NewsDTORequest(null, "testTitle", "testContent", 21L);
        assertThrows(ValidatorException.class, () -> newsService.create(entity));
    }

    @DisplayName("JUnit test for update method.")
    @Test
    public void shouldReturnUpdatedNewsDTO() {
        NewsDTORequest entity = new NewsDTORequest(2L, "testTitle", "testContent", 15L);
        NewsDTOResponse expected = new NewsDTOResponse(2L, "testTitle", "testContent", null, null, 15L);
        NewsDTOResponse result = newsService.update(entity);
        assertEquals(expected.id(), result.id());
        assertEquals(expected.title(), result.title());
        assertEquals(expected.content(), result.content());
        assertEquals(expected.authorId(), result.authorId());
    }

    @DisplayName("JUnit test for update method. Invalid news id.")
    @Test
    public void shouldThrowValidatorExceptionOnInvalidNewsId() {
        NewsDTORequest entity = new NewsDTORequest(0L, "testTitle", "testContent", 15L);
        assertThrows(ValidatorException.class, () -> newsService.update(entity));
    }

    @DisplayName("JUnit test for update method. News with Such id does not exist.")
    @Test
    public void shouldThrowNotFoundExceptionWhenNewsIdDoesNotExistForUpdate() {
        NewsDTORequest entity = new NewsDTORequest(0L, "testTitle", "testContent", 15L);
        assertThrows(ValidatorException.class, () -> newsService.update(entity));
    }

    @DisplayName("JUnit test for delete method.")
    @Test
    public void shouldReturnTrueOnDelete() {
        assertTrue(newsService.deleteById(1L));
    }
}
