package com.mjc.school.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import com.mjc.school.service.interfaces.Service;
import com.mjc.school.service.dto.NewsDTORequest;
import com.mjc.school.service.dto.NewsDTOResponse;
import com.mjc.school.service.exception.NotFoundException;
import com.mjc.school.service.mapper.NewsMapper;
import com.mjc.school.service.mapper.NewsMapperImpl;
import com.mjc.school.service.validator.NewsValidator;
import com.mjc.school.repository.interfaces.Repository;
import com.mjc.school.repository.impl.NewsRepositoryImpl;
import com.mjc.school.repository.model.NewsModel;

public class NewsServiceImpl implements Service<NewsDTOResponse, NewsDTORequest> {
    private final String NEWS_ID_DOES_NOT_EXIST = "News with id %s does not exist.";

    private final Repository<NewsModel> newsRepository = new NewsRepositoryImpl();
    private final NewsMapper newsMapper = new NewsMapperImpl();
    private final NewsValidator newsValidator = new NewsValidator();

    @Override
    public List<NewsDTOResponse> readAll() {
        return newsMapper.convertListToDTOList(newsRepository.readAll());
    }

    @Override
    public NewsDTOResponse readById(Long id) {
        this.newsValidator.validateNewsId(id);
        if (this.newsRepository.isExistById(id)) {
            return this.newsMapper.convertToDTO(this.newsRepository.readById(id));
        }
        throw new NotFoundException(String.format(NEWS_ID_DOES_NOT_EXIST, id));
    }

    @Override
    public NewsDTOResponse create(NewsDTORequest entity) {
        this.newsValidator.validateNewsDTO(entity);
        NewsModel temporaryNewsModel = newsMapper.convertToModel(entity);
        LocalDateTime date = LocalDateTime.now();
        temporaryNewsModel.setCreateDate(date);
        temporaryNewsModel.setLastUpdateDate(date);
        NewsModel newsModel = this.newsRepository.create(temporaryNewsModel);
        return this.newsMapper.convertToDTO(newsModel);
    }

    @Override
    public NewsDTOResponse update(NewsDTORequest entity) {
        this.newsValidator.validateNewsId(entity.id());
        this.newsValidator.validateNewsDTO(entity);
        if (this.newsRepository.isExistById(entity.id())) {
            NewsModel temporaNewsModel = this.newsMapper.convertToModel(entity);
            LocalDateTime date = LocalDateTime.now();
            temporaNewsModel.setLastUpdateDate(date);
            NewsModel newsModel = this.newsRepository.update(temporaNewsModel);
            return this.newsMapper.convertToDTO(newsModel);
        }
        throw new NotFoundException(String.format(NEWS_ID_DOES_NOT_EXIST, entity.id()));
    }

    @Override
    public Boolean deleteById(Long id) {
        this.newsValidator.validateNewsId(id);
        if (this.newsRepository.isExistById(id)) {
            return this.newsRepository.deleteById(id);
        }
        throw new NotFoundException(String.format(NEWS_ID_DOES_NOT_EXIST, id));
    }

}
