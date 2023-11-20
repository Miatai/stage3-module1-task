package com.mjc.school.service.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.mjc.school.repository.model.NewsModel;
import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.dto.NewsDtoResponse;

@Mapper
public interface NewsMapper {
    NewsMapper INSTANCE = Mappers.getMapper(NewsMapper.class);

    List<NewsDtoResponse> convertListToDTOList(List<NewsModel> news);

    NewsDtoResponse convertToDTO(NewsModel news);

    NewsModel convertToModel(NewsDtoRequest newsDTO);
}
