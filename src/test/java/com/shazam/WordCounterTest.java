package com.shazam;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class WordCounterTest {

    @Rule
    public JUnitRuleMockery context = new JUnitRuleMockery();

    Lines linesMock;

    @Before
    public void setUp() {
        linesMock = context.mock(Lines.class);
    }

    @Test
    public void noLines() throws Exception {
        // given
        emptyLines();
        // when
        int count = countWords();
        // then
        assertWordCount(0, count);
    }

    @Test
    public void oneLine_oneWord() throws Exception {
        // given
        lines("hello");
        // when
        int count = countWords();
        // then
        assertWordCount(1, count);
    }

    @Test
    public void oneLine_oneWord_whitespaceBeforeLine() throws Exception {
        // given
        lines("\thello");
        // when
        int count = countWords();
        // then
        assertWordCount(1, count);
    }

    @Test
    public void oneLine_oneWord_whitespaceAfterLine() throws Exception {
        // given
        lines("hello  \t");
        // when
        int count = countWords();
        // then
        assertWordCount(1, count);
    }

    @Test
    public void oneLine_multipleWords_oneWhitespaceSeparating() throws Exception {
        // given
        lines("hello there Fred");
        // when
        int count = countWords();
        // then
        assertWordCount(3, count);
    }

    @Test
    public void oneLine_multipleWords_multipleWhitespaceSeparating() throws Exception {
        // given
        lines("hello\tthere   Fred");
        // when
        int count = countWords();
        // then
        assertWordCount(3, count);
    }

    @Test
    public void multipleLines_noBlankLines() throws Exception {
        // given
        lines("hello there   ", " \t  Fred", "how are   you today");
        // when
        int count = countWords();
        // then
        assertWordCount(7, count);
    }

    @Test
    public void multipleLines_oneOfTheLinesIsBlank() throws Exception {
        // given
        lines("hello there   ", "", "Fred");
        // when
        int count = countWords();
        // then
        assertWordCount(3, count);
    }

    @Test
    public void testGetTypeCode() {
        String code = new WordCounter(linesMock).getTypeCode();

        assertEquals("-w", code);
    }

    private void emptyLines() throws Exception {
        lines();
    }

    private void lines(final String... lines) throws Exception {
        context.checking(new Expectations() {
            {
                oneOf(linesMock).get();
                will(returnValue(Arrays.asList(lines)));
            }
        });
    }

    private void assertWordCount(int expected, int actual) {
        assertEquals(expected, actual);
    }

    private int countWords() throws Exception {
        Counter wc = new WordCounter(linesMock);
        return wc.count();
    }

}
