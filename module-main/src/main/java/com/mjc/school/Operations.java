package com.mjc.school;

import java.util.Scanner;

import com.mjc.school.controller.impl.NewsControllerImpl;
import com.mjc.school.controller.interfaces.Controller;
import com.mjc.school.service.dto.NewsDTORequest;
import com.mjc.school.service.dto.NewsDTOResponse;

public class Operations {
    private final Controller<NewsDTOResponse, NewsDTORequest> controller = new NewsControllerImpl();

    public void getAllNews() {
        System.out.println(Constants.OPERATION + Constants.GET_ALL_NEWS);
        controller.readAll().forEach(System.out::println);
    }

    public void getNewsById(Scanner sc) {
        System.out.println(Constants.OPERATION + Constants.GET_NEWS_BY_ID);
        Long id;
        System.out.println(Constants.ID_INPUT);
        id = inputNumberByUser(sc);
        System.out.println(id);
        System.out.println(controller.readById(id));
    }

    public void createNews(Scanner sc) {
        System.out.println(Constants.OPERATION + Constants.CREATE_NEWS);
        String title;
        String content;
        Long authorId;
        System.out.println(Constants.TITLE_INPUT);
        title = inputStringByUser(sc);
        System.out.println(Constants.CONTENT_INPUT);
        content = inputStringByUser(sc);
        System.out.println(Constants.AUTHOR_ID_INPUT);
        authorId = inputNumberByUser(sc);
        NewsDTORequest newsRequest = new NewsDTORequest(null, title, content, authorId);
        System.out.println(controller.create(newsRequest));
    }

    public void updateNews(Scanner sc) {
        System.out.println(Constants.OPERATION + Constants.UPDATE_NEWS);
        Long id;
        String title;
        String content;
        Long authorId;
        System.out.println(Constants.ID_INPUT);
        id = inputNumberByUser(sc);
        System.out.println(Constants.TITLE_INPUT);
        title = inputStringByUser(sc);
        System.out.println(Constants.CONTENT_INPUT);
        content = inputStringByUser(sc);
        System.out.println(Constants.AUTHOR_ID_INPUT);
        authorId = inputNumberByUser(sc);
        NewsDTORequest newsRequest = new NewsDTORequest(id, title, content, authorId);
        System.out.println(controller.update(newsRequest));
    }

    public void removeNewsById(Scanner sc) {
        System.out.println(Constants.OPERATION + Constants.REMOVE_NEWS);
        Long id;
        System.out.println(Constants.ID_INPUT);
        id = inputNumberByUser(sc);
        System.out.println(controller.deleteById(id));
    }

    private Long inputNumberByUser(Scanner sc) {
        Long input = sc.nextLong();
        sc.nextLine();
        return input;
    }

    private String inputStringByUser(Scanner sc) {
        return sc.nextLine();
    }
}
