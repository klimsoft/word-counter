package pl.kamil.wordcounter;
import java.util.*;
import java.util.stream.Collectors;

public class WordCounter {
    private Map<String, Integer> counter = new HashMap<>();


    public void processLines(List<String> lines) {
        counter.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        for (String line : lines) {
            String[] words = line.split("\\s|,");
            for (String word : words) {
                String cleaned = word.trim().toLowerCase()
//                            .replaceAll("!|\\(|\\)|,|:|\\.", "");
                        .replaceAll("[!\\(\\),:\\.;—?]", "");

                if (!cleaned.equals("")) {
                    counter.compute(cleaned, (k, v) -> (v == null) ? 1 : v + 1);

                }
            }
        }
    }

    public List<Map.Entry<String, Integer>> getMostPopular(int count) {
        return counter.entrySet().stream()
                .sorted(Collections.reverseOrder(Comparator.comparing(Map.Entry::getValue)))
                .limit(count)
                .collect(Collectors.toList());
    }

    public List<Map.Entry<String, Integer>> getWithNumberOfOccurances(int count) {
        return counter.entrySet().stream()
                .sorted(Collections.reverseOrder(Comparator.comparing(Map.Entry::getValue)))
                .filter(x -> x.getValue().equals(count))
                .collect(Collectors.toList());
    }
}
