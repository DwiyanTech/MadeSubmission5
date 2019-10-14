package com.dwiyan.tvandmoviecatalogue.supportConfig;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateConfig {

public static   String DateConfigFormat(){

    Calendar calendar = Calendar.getInstance();

    Date date = calendar.getTime();

    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    String nowDateFormat = dateFormat.format(date);

    return nowDateFormat;
}

}
