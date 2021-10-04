package me.minemis.calculator;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        String test = "55 + ";
        String[] split = test.split(" ");

        for (String s : split) {
            System.out.println(s);
        }

        assertEquals(2, split.length);
    }
}