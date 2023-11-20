package com.mjc.school.repository.data;

import java.util.ArrayList;
import java.util.List;

import com.mjc.school.repository.model.AuthorModel;
import com.mjc.school.repository.utils.Utils;

public class AuthorDataSource {
    private static volatile AuthorDataSource INSTANCE;

    public final String AUTHOR_FILE_NAME = "/authors.txt";
    private List<AuthorModel> authorList;

    private AuthorDataSource() {
        init();
    }

    private void init() {
        this.authorList = new ArrayList<>();
        for (long i = 1L; i <= 20L; i++) {
            this.authorList.add(new AuthorModel(i, Utils.getRandomContentByFilePath(AUTHOR_FILE_NAME)));
        }
    }

    public static AuthorDataSource getInstance() {
        if (INSTANCE == null) {
            synchronized (AuthorDataSource.class) {
                if (INSTANCE == null) {
                    INSTANCE = new AuthorDataSource();
                }
            }
        }
        return INSTANCE;
    }

    public List<AuthorModel> getAuthorData(){
        return this.authorList;
    }
}
