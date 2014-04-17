package com.shazam;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class LineCounterTest {

    @Rule
    public JUnitRuleMockery context = new JUnitRuleMockery();

    private Lines linesMock;

    @Before
    public void setUp() {
        linesMock = context.mock(Lines.class);
    }

    @Test
    public void noLines() throws Exception {
        // Given
        fileLines();
        // When
        int count = countLines();
        // Then
        assertEquals(0, count);
    }

    @Test
    public void oneLine() throws Exception {
        // Given
        fileLines("hello");
        // When
        int count = countLines();
        // Then
        assertEquals(1, count);
    }

    @Test
    public void multipleLines() throws Exception {
        // Given
        fileLines("hello", "", "there\t");
        // When
        int count = countLines();
        // Then
        assertEquals(3, count);
    }

    @Test
    public void testGetTypeCode() {
        String code = new LineCounter(linesMock).getTypeCode();

        assertEquals("-l", code);
    }

    private int countLines() throws Exception {
        LineCounter lc = new LineCounter(linesMock);
        return lc.count();
    }

    private void fileLines(final String... lines) throws Exception {
        context.checking(new Expectations() {
            {
                oneOf(linesMock).get();
                will(returnValue(Arrays.asList(lines)));
            }
        });
    }

}
