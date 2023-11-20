package com.mjc.school.service.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.mjc.school.repository.model.NewsModel;
import com.mjc.school.service.dto.NewsDTORequest;
import com.mjc.school.service.dto.NewsDTOResponse;

@Mapper
public interface NewsMapper {
    NewsMapper INSTANCE = Mappers.getMapper(NewsMapper.class);

    List<NewsDTOResponse> convertListToDTOList(List<NewsModel> news);

    NewsDTOResponse convertToDTO(NewsModel news);

    NewsModel convertToModel(NewsDTORequest newsDTO);
}
