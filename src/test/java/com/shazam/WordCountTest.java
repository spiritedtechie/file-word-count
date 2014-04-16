package com.shazam;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class WordCountTest {

    @Rule
    public JUnitRuleMockery context = new JUnitRuleMockery();

    Lines linesMock;

    @Before
    public void setUp() {
        linesMock = context.mock(Lines.class);
    }

    @Test
    public void oneLine_empty() throws Exception {
        // given
        oneEmptyLine();
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
        lines("hello\tthere          Fred");
        // when
        int count = countWords();
        // then
        assertWordCount(3, count);
    }

    @Test
    public void multipleLines_multipleWords() throws Exception {
        // given
        lines("hello  there          ", " \t  Fred", "how are    you today");
        // when
        int count = countWords();
        // then
        assertWordCount(7, count);
    }

    private void oneEmptyLine() throws Exception {
        lines("");
    }

    private void lines(final String... lines) throws Exception {
        context.checking(new Expectations() {
            {
                oneOf(linesMock).get();
                will(returnValue(Arrays.asList(lines)));
            }
        });
    }

    private void assertWordCount(final int expected, final int actual) {
        assertEquals(expected, actual);
    }

    private int countWords() throws Exception {
        WordCount wc = new WordCount(linesMock);
        return wc.count();
    }

}
