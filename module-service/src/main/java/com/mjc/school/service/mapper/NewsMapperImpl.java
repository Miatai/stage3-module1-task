package com.mjc.school.service.mapper;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.dto.NewsDtoResponse;
import com.mjc.school.repository.model.NewsModel;

public class NewsMapperImpl implements NewsMapper {

    @Override
    public NewsDtoResponse convertToDTO(NewsModel newsModel) {
        if (newsModel == null) {
            return null;
        }
        Long id = newsModel.getId();
        String title = newsModel.getTitle();
        String content = newsModel.getContent();
        LocalDateTime createDate = newsModel.getCreateDate();
        LocalDateTime lastUpdateDate = newsModel.getLastUpdateDate();
        Long authorId = newsModel.getAuthorId();
        NewsDtoResponse newsDTO = new NewsDtoResponse(id, title, content, createDate, lastUpdateDate, authorId);
        return newsDTO;
    }

    @Override
    public NewsModel convertToModel(NewsDtoRequest newsDTO) {
        if (newsDTO == null) {
            return null;
        }
        Long id = newsDTO.id();
        String title = newsDTO.title();
        String content = newsDTO.content();
        LocalDateTime createDate = null;
        LocalDateTime lastUpdateDate = null;
        Long authorId = newsDTO.authorId();
        NewsModel newsModel = new NewsModel(id, title, content, createDate, lastUpdateDate, authorId);
        return newsModel;
    }

    @Override
    public List<NewsDtoResponse> convertListToDTOList(List<NewsModel> newsModelList) {
        if (newsModelList == null) {
            return null;
        }
        List<NewsDtoResponse> newsDTOList = new ArrayList<>();
        for (NewsModel newsModel : newsModelList) {
            newsDTOList.add(convertToDTO(newsModel));
        }
        return newsDTOList;
    }

}
