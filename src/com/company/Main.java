package com.company;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        String pathToWarAndPeace = "src/resources/voyna.txt";
        Path path = Paths.get(pathToWarAndPeace);
        Scanner input = new Scanner(path);

        String substring = "страда"; //по этой подстроке буду искать совпадения

        List<String> strText; //в этот список буду класть всю строку текста
        List<String> duplicates = new ArrayList<>(); //сюда будут падать найденные слова с дубликатами
        Set<String> noDuplicates = new HashSet<>(); //сюда будут падать в Set, чтобы уже сразу исключить дубликаты
        HashMap<String, Integer> mapSortByValue = new HashMap<>(); //ну а сюда, чтоб красива вывести и отсортировать по количеству дубликатов

        while (input.hasNext()){
            String lineText = input.nextLine(); //из Scanner получаю строку текста
            strText = Arrays.asList(lineText.replaceAll("[^a-zA-ZА-Яа-я0-9]", " ").trim().split(" ")); //бью эту строку на слова
            for (String item: strText) { //перебираю элементы каждой строки
                if (item.startsWith(substring)) { //проверяю, что слова начинаются на нужную мне подстроку
                    duplicates.add(item); //сюда кладем все найденные слова
                    noDuplicates.add(item);  //сюда без дубликатов
                }
            }
        }
        input.close();

        int countRepeat = 0; //счетчик повторений
        int total = 0; //счетчик суммы повторений
        for (String item: noDuplicates) {
            countRepeat = Collections.frequency(duplicates, item); //проверяем на повторения
            mapSortByValue.put(item, countRepeat); //записываем в мапу ключ + значение (количество повторений)
            total += countRepeat; //суммируем повторения
        }
        //сортируем мапу по значению. Это кнеш для меня самое сложное было, не доводилось. Со всей силы подсмотрел интернетике
        mapSortByValue.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEach(System.out::println);
        System.out.println("TOTAL: " + total);
    }
}