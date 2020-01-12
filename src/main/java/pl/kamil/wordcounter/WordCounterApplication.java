package pl.kamil.wordcounter;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class WordCounterApplication {


    public static void main(String[] args) throws Exception {
        //hardcoded path
        //Path path = Paths.get("c:/Users/lenovo/downloads/pan-tadeusz.txt");

        //Path from resources
        Path path = Paths.get(WordCounterApplication.class.getClassLoader().getResource("pan-tadeusz.txt").toURI());

        List<String> lines = Files.readAllLines(path, Charset.forName("UTF-8"));

        WordCounter wordCounter = new WordCounter();
        wordCounter.processLines(lines);
        wordCounter.getMostPopular(10).stream().forEach(System.out::println);
    }

}
