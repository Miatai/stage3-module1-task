package com.mjc.school.service.validator;

import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.exception.ValidatorException;

public class NewsValidator {
    private static final int NEWS_TITLE_LENGHT_MIN = 5;
    private static final int NEWS_TITLE_LENGHT_MAX = 30;
    private static final int NEWS_CONTENT_LENGHT_MIN = 5;
    private static final int NEWS_CONTENT_LENGHT_MAX = 255;
    private static final Long MIN_ID = 1L;
    private static final Long MAX_AUTHOR_ID = 20L;
    private static final String AUTHOR_ID_MORE_THAN_MAX_AUTHOR_ID = "Author id cannot be more than %s. Author id is: %s.";
    private static final String NEGATIVE_OR_NULL_NUMBER = "%s cannot be null or less than 1. %s is: %s.";
    private static final String AUTHOR_ID = "Author id";
    private static final String NEWS_ID = "News id";
    private static final String NEWS_TITLE = "News title";
    private static final String NEWS_CONTENT = "News content";
    private static final String STRING_LENGHT_ERROR = "%s lenght cannot be less than %s and more than %s. %s lenght is: %s.";
    private static final String NULL_STRING = "% cannot be null.";

    public void validateNewsId(Long id) {
        validateId(id, NEWS_ID);
    }

    private void validateAuthorId(Long id) {
        validateId(id, AUTHOR_ID);
        if (id > MAX_AUTHOR_ID) {
            throw new ValidatorException(String.format(AUTHOR_ID_MORE_THAN_MAX_AUTHOR_ID, MAX_AUTHOR_ID, id));
        }
    }

    private void validateId(Long id, String type) {
        if (id == null || id < MIN_ID) {
            throw new ValidatorException(String.format(NEGATIVE_OR_NULL_NUMBER, type, type, id));
        }
    }

    public void validateNewsDTO(NewsDtoRequest newsDTO) {
        validateString(newsDTO.title(), NEWS_TITLE_LENGHT_MIN, NEWS_TITLE_LENGHT_MAX, NEWS_TITLE);
        validateString(newsDTO.content(), NEWS_CONTENT_LENGHT_MIN, NEWS_CONTENT_LENGHT_MAX, NEWS_CONTENT);
        validateAuthorId(newsDTO.authorId());
    }

    private void validateString(String value, int minLenght, int maxLenght, String type) {
        if (value == null) {
            throw new ValidatorException(String.format(NULL_STRING, type));
        }
        if (value.length() < minLenght || value.length() > maxLenght) {
            throw new ValidatorException(String.format(STRING_LENGHT_ERROR, type, minLenght,
                    maxLenght, type, value.length()));
        }
    }
}
