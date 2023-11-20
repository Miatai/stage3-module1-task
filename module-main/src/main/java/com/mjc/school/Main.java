package com.mjc.school;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Operations operations = new Operations();
        while (true) {
            try {
                System.out.println("Enter the number of operation: ");
                System.out.println("1 - " + Constants.GET_ALL_NEWS);
                System.out.println("2 - " + Constants.GET_NEWS_BY_ID);
                System.out.println("3 - " + Constants.CREATE_NEWS);
                System.out.println("4 - " + Constants.UPDATE_NEWS);
                System.out.println("5 - " + Constants.REMOVE_NEWS);
                System.out.println("0 - " + Constants.EXIT);
                String input = sc.nextLine();
                switch (input) {
                    case "1" -> {
                        operations.getAllNews();
                    }
                    case "2" -> {
                        operations.getNewsById(sc);
                    }
                    case "3" -> {
                        operations.createNews(sc);
                    }
                    case "4" -> {
                        operations.updateNews(sc);
                    }
                    case "5" -> {
                        operations.removeNewsById(sc);
                    }
                    case "0" -> System.exit(0);
                    default -> System.out.println(Constants.COMMAND_NOT_FOUND);
                }
            } catch (RuntimeException e) {
                System.err.println("ERROR MESSAGE: " + e.getMessage());
            }
        }
    }
}
