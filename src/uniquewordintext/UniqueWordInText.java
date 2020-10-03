/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uniquewordintext;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * @author YNZ
 */
public class UniqueWordInText {

    final private static String pattern = "[;:!?.,\\s]+";

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, InterruptedException {
        Instant start = Instant.now();

        List<Map.Entry<String, Integer>> top10 = wordOccurrence();

        Instant end = Instant.now();
        System.out.println("Top 10 word: " + top10.toString());
        System.out.println("Time cost  :" + Duration.between(start, end).toMillis());

        printOutAnotherWordOccurrence();
    }

    private static void printOutAnotherWordOccurrence() throws IOException {
        System.out.println("++++++++++++++ another method ++++++++++++++++++++");
        Instant start = Instant.now();
        List<Map.Entry<Integer, String>> top10 = anotherWordOccurrence();

        Instant end = Instant.now();
        System.out.println("Top 10 word: " + top10.toString());
        System.out.println("Time cost  :" + Duration.between(start, end).toMillis());
        System.out.println("++++++++++++++ another method +++++++++++++++++++++");
    }

    public static List<Map.Entry<String, Integer>> wordOccurrence() throws IOException {

        String str = Files.readString(Paths.get("tempest.txt"));

        List<String> wordsList = Stream.of(str.toLowerCase(Locale.forLanguageTag("en")).split(pattern))
                .collect(toList());

        HashMap<String, Integer> wordCountMap = new HashMap<>();
        wordsList.forEach(w -> {
            if (wordCountMap.containsKey(w)) {
                int wordCount = wordCountMap.get(w);
                wordCount++;
                wordCountMap.put(w, wordCount);
            } else {
                wordCountMap.put(w, 1);
            }
        });

        return wordCountMap.entrySet().stream().sorted((o1, o2) -> o2.getValue().compareTo(o1.getValue()))
                .limit(10).collect(toList());
    }

    public static List<Map.Entry<Integer, String>> anotherWordOccurrence() throws IOException {

        String str = Files.readString(Paths.get("tempest.txt"));

        List<String> wordsList = Stream.of(str.toLowerCase(Locale.forLanguageTag("en")).split(pattern))
                .collect(toList());

        HashMap<String, Integer> wordCountMap = new HashMap<>();
        wordsList.forEach(w -> {
            if (wordCountMap.containsKey(w)) {
                int wordCount = wordCountMap.get(w);
                wordCount++;
                wordCountMap.put(w, wordCount);
            } else {
                wordCountMap.put(w, 1);
            }
        });

        NavigableMap<Integer, String> sortedCountWordMap = wordCountMap.entrySet().stream().collect(Collectors.toMap(
                entry -> entry.getValue(),
                entry -> entry.getKey(),
                (o, n) -> n,
                TreeMap::new)
        );

        return sortedCountWordMap.descendingMap().entrySet()
                .stream().limit(10).collect(toList());

    }


}
