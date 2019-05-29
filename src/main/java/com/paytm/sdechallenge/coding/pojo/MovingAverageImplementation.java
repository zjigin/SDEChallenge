package com.paytm.sdechallenge.coding.pojo;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author zji
 */
public class MovingAverageImplementation implements MovingAverageInterface {

    private Integer cacheLength;

    /**
     * slice keeps last N elements.
     */
    private Queue<Double> slice = new ConcurrentLinkedQueue<>();

    private Double cachedAverage = 0.0;

    /**
     * @param n size of the last N elements
     */
    public MovingAverageImplementation(Integer n) {
        this.cacheLength = n;
    }

    /**
     * Add input element into slice,
     * null value is ignored.
     * O(1) to add element and O(1) to get the average of the slice.
     *
     * @param inputElement input element
     */
    @Override
    public synchronized void addElement(Double inputElement) {
        // Ignore null value, maybe throw a warning in log.
        if(inputElement == null) {
            return;
        }

        if(this.slice.size() < this.cacheLength) {
            this.slice.add(inputElement);
            this.cachedAverage = (this.cachedAverage * (this.slice.size() - 1) + inputElement)/this.slice.size();
        } else {
            this.slice.add(inputElement);
            // slice size is above N, poll the first element
            Double firstElement = this.slice.poll();
            //noinspection ConstantConditions
            this.cachedAverage = (this.cachedAverage * this.cacheLength - firstElement + inputElement)/this.cacheLength;
        }
    }

    @Override
    public Double getAverageForLastNElements() {
        return this.cachedAverage;
    }

    /**
     * This method is another implementation.
     * Should use this implementation if accuracy is not our first priority even though it's run time is O(n).
     *
     * @return Double average of the last N elements
     */
    Double calculateAverageForLastNElements() {
        Double sum = 0.0;
        for (Double element: this.slice) {
            sum += element;
        }
        return sum/(double)(this.slice.size());
    }

}
