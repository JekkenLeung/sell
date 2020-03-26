package com.jekken.utils;

/**
 * Create by Jekken
 * 2020/3/26 21:27
 */
public class MathUtil {

    private static final Double MONEY_RANGE = 0.01;

    /**
     * 比较2个金额是否想等
     */
    public static Boolean equals(Double d1,Double d2){
        Double result=Math.abs(d1-d2);
        if (result<MONEY_RANGE){
            return true;
        }else {
            return false;
        }
    }

}
