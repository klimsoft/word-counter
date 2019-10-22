package pl.kamil.wordcounter;


import org.assertj.core.api.Assertions;
import org.assertj.core.data.MapEntry;
import org.junit.Test;

import java.util.Arrays;

public class WordCounterTest {
	private WordCounter wordCounter = new WordCounter();

	@Test
	public void shouldCountWordsSeparatedBySpaces() {
		String line = "kot pies kot";

		wordCounter.processLines(Arrays.asList(line));

		Assertions.assertThat(wordCounter.getMostPopular(3))
				.containsExactly(MapEntry.entry("kot", 2), MapEntry.entry("pies", 1));
	}

	@Test
	public void shouldIgnoreSpecialCharactersAfterWord() {
		String line = "kot, kot? kot!";

		wordCounter.processLines(Arrays.asList(line));

		Assertions.assertThat(wordCounter.getMostPopular(1))
				.containsExactly(MapEntry.entry("kot", 3));
	}

	@Test
	public void shouldLowercaseWords() {
		String line = "PIES pies Pies PiEs";

		wordCounter.processLines(Arrays.asList(line));

		Assertions.assertThat(wordCounter.getMostPopular(1))
				.containsExactly(MapEntry.entry("pies", 4));
	}

	@Test
	public void shouldProcessAllLines() {
		String line1 = "pies";
		String line2 = "pies kot";

		wordCounter.processLines(Arrays.asList(line1, line2));

		Assertions.assertThat(wordCounter.getMostPopular(2))
				.containsExactly(MapEntry.entry("pies", 2), MapEntry.entry("kot", 1));
	}

	@Test
	public void shouldLimitOutputToTheGivenNumberOfItems() {
		String line = "kot kot kot pies pies lis wilk";

		wordCounter.processLines(Arrays.asList(line));

		Assertions.assertThat(wordCounter.getMostPopular(2))
				.containsExactly(MapEntry.entry("kot", 3), MapEntry.entry("pies", 2));
	}

	@Test
	public void shouldSeparateWordsByComma() {
		String line = "kot,kot";

		wordCounter.processLines(Arrays.asList(line));

		Assertions.assertThat(wordCounter.getMostPopular(1))
				.containsExactly(MapEntry.entry("kot", 2));
	}
}
