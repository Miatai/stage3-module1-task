package com.mjc.school.service.mapper;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.mjc.school.service.dto.NewsDTORequest;
import com.mjc.school.service.dto.NewsDTOResponse;
import com.mjc.school.repository.model.NewsModel;

public class NewsMapperImpl implements NewsMapper {

    @Override
    public NewsDTOResponse convertToDTO(NewsModel newsModel) {
        if (newsModel == null) {
            return null;
        }
        Long id = newsModel.getId();
        String title = newsModel.getTitle();
        String content = newsModel.getContent();
        LocalDateTime createDate = newsModel.getCreateDate();
        LocalDateTime lastUpdateDate = newsModel.getLastUpdateDate();
        Long authorId = newsModel.getAuthorId();
        NewsDTOResponse newsDTO = new NewsDTOResponse(id, title, content, createDate, lastUpdateDate, authorId);
        return newsDTO;
    }

    @Override
    public NewsModel convertToModel(NewsDTORequest newsDTO) {
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
    public List<NewsDTOResponse> convertListToDTOList(List<NewsModel> newsModelList) {
        if (newsModelList == null) {
            return null;
        }
        List<NewsDTOResponse> newsDTOList = new ArrayList<>();
        for (NewsModel newsModel : newsModelList) {
            newsDTOList.add(convertToDTO(newsModel));
        }
        return newsDTOList;
    }

}
