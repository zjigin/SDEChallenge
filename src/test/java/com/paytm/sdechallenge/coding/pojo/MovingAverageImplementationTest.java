package com.paytm.sdechallenge.coding.pojo;

import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertEquals;

public class MovingAverageImplementationTest {

    // Null case and some boundary case is tested in mind :)

    @Test
    public void testGetAverageForLastNElements() {
        int testTimes = 10;

        Random random = new Random();
        random.setSeed(System.currentTimeMillis());

        // N is between 10 to 107
        int n_base = 10;
        int n_max = 97;

        // Number of total elements added is between 10 to 100
        int numberOfTotalElementsBase = 10;
        int numberOfTotalElementsMax = 90;

        for(int i=0;i<testTimes;i++) {
            Integer n = random.nextInt(n_max) + n_base;
            MovingAverageImplementation movingAverage = new MovingAverageImplementation(n);
            Integer numberOfTotalElements = random.nextInt(numberOfTotalElementsMax) + numberOfTotalElementsBase;
            for (Double j = 0.0; j < numberOfTotalElements; j++) {
                movingAverage.addElement(random.nextDouble());
                /*
                 * Compare the results by using two different methods.
                 * Only cares about the first 10 digits after the dot by assuming speed is our first priority instead of accuracy.
                 */
                assertEquals(String.format("%.10f", movingAverage.calculateAverageForLastNElements()), String.format("%.10f", movingAverage.getAverageForLastNElements()));
            }
        }
    }

}
