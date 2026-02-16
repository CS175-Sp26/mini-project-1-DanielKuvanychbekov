package edu.sjsu.android.project1danielkuvan;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    private void myTest(double P, double rate, int years, boolean withTax, double expected) {
        double actual = Calculator.calculateResult(P, rate, years, withTax);
        assertEquals(expected, actual,  0.01);
    }

    @Test
    public void testWithoutTax() {
        myTest (10000.9 ,5.5, 15, false, 81.71);
    }

    @Test
    public void testNoInterestRate() {
        myTest (20000.0 ,0.0, 20, false, 83.33);
    }

    @Test
    public void testWithTax() {
        myTest (20000.0 ,10.0, 20, true, 213.00);
    }
}