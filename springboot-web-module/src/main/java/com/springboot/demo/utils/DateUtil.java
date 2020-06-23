package com.springboot.demo.utils;

import org.apache.commons.lang3.time.DateUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil extends DateUtils {

    private DateUtil() {}

    public static String dateToString(Date date,String format) {
//        yyyyMMddHHmmss
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        String format1 = simpleDateFormat.format(date);
        return format1;
    }

    public static void main(String[] args) {
        System.out.println(dateToString(new Date(), "yyyyMMddHHmmss"));
        System.out.println("20200614144429".length());
    }
}
