/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uniquewordintext;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.Comparator;
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
        List<Map.Entry<String, Integer>> top10 = wordOccurrence();
        System.out.println("Top 10 word: " + top10.toString());

        printOutAnotherWordOccurrence();
    }

    private static void printOutAnotherWordOccurrence() throws IOException {
        System.out.println("++++++++++++++ another method ++++++++++++++++++++");
        List<Map.Entry<Integer, String>> top10 = anotherWordOccurrence();

        Instant end = Instant.now();
        System.out.println("Top 10 word: " + top10.toString());
        System.out.println("++++++++++++++ another method +++++++++++++++++++++");
    }

    public static List<Map.Entry<String, Integer>> wordOccurrence() throws IOException {
        String str = Files.readString(Paths.get("tempest.txt"));

        List<String> wordsList = Stream.of(str.toLowerCase(Locale.forLanguageTag("en"))
                .split(pattern))
                .collect(toList());
        Map<String, Integer> wordCountMap = countOccurrences(wordsList);

        Comparator<Map.Entry<String, Integer>> comparator = (e1, e2) -> {
            if (e1.getValue() == e2.getValue()) {
                return e1.getKey().compareTo(e2.getKey());
            } else {
                return e1.getValue().compareTo(e2.getValue());
            }
        };

        return wordCountMap.entrySet().stream().sorted(comparator.reversed()).limit(10).collect(toList());
    }

    //traverse words and count their occurrences.
    private static Map<String, Integer> countOccurrences(List<String> wordList) {
        Map<String, Integer> wordCountMap = new HashMap<>();

        wordList.forEach(w -> {
            Integer c = wordCountMap.get(w);
            if (c != null) {
                c++;
                wordCountMap.put(w, c);
            } else {
                wordCountMap.put(w, 1);
            }
        });

        return wordCountMap;
    }

    public static List<Map.Entry<Integer, String>> anotherWordOccurrence() throws IOException {

        String str = Files.readString(Paths.get("tempest.txt"));

        List<String> wordsList = Stream.of(str.toLowerCase(Locale.forLanguageTag("en"))
                .split(pattern))
                .collect(toList());

        Map<String, Integer> wordCountMap = countOccurrences(wordsList);

        NavigableMap<Integer, String> sortedCountWordMap = wordCountMap.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getValue,
                        Map.Entry::getKey,
                        (o, n) -> n,
                        TreeMap::new)
                );

        return sortedCountWordMap.descendingMap().entrySet().stream().limit(10).collect(toList());
    }

}
