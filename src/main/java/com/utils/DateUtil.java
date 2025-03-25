package com.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    public static String getDateInyyyyMMddhhmmssFormat(){
        return new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
    }

}
