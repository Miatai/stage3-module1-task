package com.mjc.school.repository.impl;

import java.util.List;

import com.mjc.school.repository.data.DataSource;
import com.mjc.school.repository.interfaces.Repository;
import com.mjc.school.repository.model.NewsModel;

public class NewsRepositoryImpl implements Repository<NewsModel> {
    private final DataSource dataSource = DataSource.getInstance();

    @Override
    public List<NewsModel> readAll() {
        return this.dataSource.getNews();
    }

    @Override
    public NewsModel readById(Long id) {
        return this.dataSource.getNews()
                .stream()
                .filter(news -> id.equals(news.getId()))
                .findFirst()
                .get();
    }

    @Override
    public NewsModel create(NewsModel entity) {
        long maxId = this.dataSource.getNews().stream().mapToLong(NewsModel::getId).max().orElse(1L);
        entity.setId(maxId + 1L);
        this.dataSource.getNews().add(entity);
        return entity;
    }

    @Override
    public NewsModel update(NewsModel entity) {
        NewsModel newsModel = readById(entity.getId());
        newsModel.setAuthorId(entity.getAuthorId());
        newsModel.setContent(entity.getContent());
        newsModel.setLastUpdateDate(entity.getLastUpdateDate());
        newsModel.setTitle(entity.getTitle());
        return newsModel;
    }

    @Override
    public boolean deleteById(Long id) {
        return this.dataSource.getNews().removeIf(news -> news.getId().equals(id));
    }

    @Override
    public Boolean isExistById(Long id) {
        return this.dataSource.getNews().stream().anyMatch(news -> news.getId().equals(id));
    }

}
