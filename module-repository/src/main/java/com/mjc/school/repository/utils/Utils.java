package com.mjc.school.repository.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Utils {

    public static String getRandomContentByFilePath(String fileName) {
        Random r = new Random();
        int numLines = 30;
        try (InputStream is = Utils.class.getResourceAsStream(fileName)) {
            if (null == is) {
                throw new FileNotFoundException(fileName);
            }
            try (BufferedReader in = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {
                int desiredLine = r.nextInt(numLines);
                int lineCtr = 0;
                String resultLine;
                while ((resultLine = in.readLine()) != null &&
                        lineCtr != desiredLine)
                    lineCtr++;
                return resultLine;
            }
        } catch (IOException e) {
            System.err.println("File not found.");
            e.printStackTrace();
            return "";
        }
    }

    public static List<String> loadDataFromFile(String fileName) {
        List<String> list = new ArrayList<>();
        try (InputStream inputStream = Utils.class.getClassLoader().getResourceAsStream(fileName)) {
            try (Scanner sc = new Scanner(inputStream)) {
                String line;
                while (sc.hasNextLine()) {
                    line = sc.nextLine();
                    list.add(line);
                }
            }
        } catch (IOException e) {
            System.err.println("File not found");
            e.printStackTrace();
        }
        return list;
    }

    public static <T> T getRandomLineFromList(List<T> list) {
        Random randomGenerator = new Random();
        int index = randomGenerator.nextInt(list.size());
        return list.get(index);
    }

    public static LocalDateTime getRandomDate() {
        Random random = new Random();
        int endDay = 30;
        LocalDate day = LocalDate.now().plusDays(random.nextInt(endDay));
        int hour = random.nextInt(24);
        int minute = random.nextInt(60);
        int second = random.nextInt(60);
        LocalTime time = LocalTime.of(hour, minute, second);
        return LocalDateTime.of(day, time);
    }
}
