package com.mjc.school.repository.data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.mjc.school.repository.model.AuthorModel;
import com.mjc.school.repository.model.NewsModel;
import com.mjc.school.repository.utils.Utils;

public class NewsDataSource {
    private static volatile NewsDataSource INSTANCE;

    private final String NEWS_FILE_NAME = "/news.txt";
    private final String CONTENT_FILE_NAME = "/content.txt";
    private List<NewsModel> newsList;

    private NewsDataSource(List<AuthorModel> authorList) {
        init(authorList);
    }

    private void init(List<AuthorModel> authorList) {
        this.newsList = new ArrayList<>();
        // List<String> rawNewslist = Utils.loadDataFromFile(NEWS_FILE_NAME);
        // List<String> rawContentList = Utils.loadDataFromFile(CONTENT_FILE_NAME);
        for (long i = 1L; i <= 20L; i++) {
            LocalDateTime date = Utils.getRandomDate();
            this.newsList.add(new NewsModel(i, Utils.getRandomContentByFilePath(NEWS_FILE_NAME),
                    Utils.getRandomContentByFilePath(CONTENT_FILE_NAME), date, date,
                    Utils.getRandomLineFromList(authorList).getId()));
        }

    }

    public static NewsDataSource getInstance(List<AuthorModel> authorList) {
        if (INSTANCE == null) {
            synchronized (NewsDataSource.class) {
                if (INSTANCE == null) {
                    INSTANCE = new NewsDataSource(authorList);
                }
            }
        }
        return INSTANCE;
    }

    public List<NewsModel> getNewsData(){
        return this.newsList;
    }

}
