package com.mjc.school.repository.data;

import java.util.List;

import com.mjc.school.repository.model.AuthorModel;
import com.mjc.school.repository.model.NewsModel;

public class DataSource {
    private static volatile DataSource INSTANCE;
    private final List<NewsModel> news;

    private DataSource() {
        List<AuthorModel> authorList = AuthorDataSource.getInstance().getAuthorData();
        this.news = NewsDataSource.getInstance(authorList).getNewsData();
    }

    public static DataSource getInstance() {
        if (INSTANCE == null) {
            synchronized (DataSource.class) {
                if (INSTANCE == null) {
                    INSTANCE = new DataSource();
                }
            }
        }
        return INSTANCE;
    }

    public List<NewsModel> getNews() {
        return this.news;
    }
}
