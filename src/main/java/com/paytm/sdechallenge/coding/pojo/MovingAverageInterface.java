package com.paytm.sdechallenge.coding.pojo;

/**
 * @author zji
 */
public interface MovingAverageInterface {

    /**
     * add input element,
     * null input is ignored.
     *
     * @param inputElement input element
     */
    void addElement(Double inputElement);

    /**
     * get the average of the last N elements,
     * N should be defined in the implementation's constructor.
     *
     * @return Double the average of last N elements
     */
    Double getAverageForLastNElements();

}
