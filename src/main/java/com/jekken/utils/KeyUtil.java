package com.jekken.utils;

import java.util.Random;

/**
 * Create by Jekken
 * 2020/3/23 17:50
 */
public class KeyUtil {

    public static synchronized String genUniqueKey(){
        Random random = new Random();
        Integer number = random.nextInt(900000)+100000;
        return System.currentTimeMillis()+String.valueOf(number);
    }

}
