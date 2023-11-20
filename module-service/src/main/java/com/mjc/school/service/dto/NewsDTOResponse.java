package com.mjc.school.service.dto;

import java.time.LocalDateTime;

public record NewsDTOResponse(Long id, String title, String content, LocalDateTime createDate,
                LocalDateTime lastUpdateDate, Long authorId) {

}
