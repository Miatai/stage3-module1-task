package com.mjc.school.controller.impl;

import java.util.List;

import com.mjc.school.controller.interfaces.Controller;
import com.mjc.school.service.interfaces.Service;
import com.mjc.school.service.dto.NewsDTORequest;
import com.mjc.school.service.dto.NewsDTOResponse;
import com.mjc.school.service.impl.NewsServiceImpl;

public class NewsControllerImpl implements Controller<NewsDTOResponse, NewsDTORequest> {
    private final Service<NewsDTOResponse, NewsDTORequest> service = new NewsServiceImpl();

    @Override
    public List<NewsDTOResponse> getAll() {
        return this.service.getAll();
    }

    @Override
    public NewsDTOResponse getById(long id) {
        return this.service.getById(id);
    }

    @Override
    public NewsDTOResponse create(NewsDTORequest entity) {
        return this.service.create(entity);
    }

    @Override
    public NewsDTOResponse update(NewsDTORequest entity) {
        return this.service.update(entity);
    }

    @Override
    public boolean deleteById(long id) {
        return this.service.deleteById(id);
    }

}
