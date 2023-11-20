package com.mjc.school.controller.impl;

import java.util.List;

import com.mjc.school.controller.interfaces.Controller;
import com.mjc.school.service.interfaces.Service;
import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.dto.NewsDtoResponse;
import com.mjc.school.service.impl.NewsServiceImpl;

public class NewsControllerImpl implements Controller<NewsDtoResponse, NewsDtoRequest> {
    private final Service<NewsDtoResponse, NewsDtoRequest> newsService = new NewsServiceImpl();

    @Override
    public List<NewsDtoResponse> readAll() {
        return this.newsService.readAll();
    }

    @Override
    public NewsDtoResponse readById(Long id) {
        return this.newsService.readById(id);
    }

    @Override
    public NewsDtoResponse create(NewsDtoRequest entity) {
        return this.newsService.create(entity);
    }

    @Override
    public NewsDtoResponse update(NewsDtoRequest entity) {
        return this.newsService.update(entity);
    }

    @Override
    public Boolean deleteById(Long id) {
        return this.newsService.deleteById(id);
    }

}
