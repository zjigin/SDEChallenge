package com.paytm.sdechallenge.coding.main;

import com.paytm.sdechallenge.coding.pojo.MovingAverageImplementation;
import com.paytm.sdechallenge.coding.pojo.MovingAverageInterface;

/**
 * @author zji
 */
public class MovingAverageMain {

    /**
     * this is a dummy main,
     * check test for details.
     */
    public static void main(String[] args) {
        Integer n = 16;
        MovingAverageInterface movingAverage = new MovingAverageImplementation(n);
        Integer testLoopLength = 20;
        for(Double i=0.0;i<testLoopLength;i++) {
            movingAverage.addElement(i);

            System.out.println("[" + i + "]");
            System.out.println(String.format("last %d average: %.3f", n, movingAverage.getAverageForLastNElements()));
        }
    }

}
