package com.dwiyan.tvandmoviecatalogue.Notification;

import android.content.Context;
import android.content.SharedPreferences;

public class AppCompactPreferences  {


    public final static String KEY_REMINDER_DAILY = "DailyReminder";
    public final static String KEY_REMINDER_RELEASE = "ReleaseReminder";
    public final static String KEY_REMINDER_MESSAGE_Release = "reminderMessageRelease";
    public final static String KEY_REMINDER_MESSAGE_Daily = "reminderMessageDaily";
    public final static String PREF_NAME = "reminderPreferences";
    public final static String CHECKED_KEY = "DAILYCHECKER_TRUE_OR_FALSE";
    public static final String KEY_HEADER_RELEASE_REMINDER = "upcomingReminder";
    public static final String KEY_HEADER_DAILY_REMINDER = "dailyReminder";
    public static final String KEY_FIELD_RELEASE_REMINDER = "checkedRelease";
    public static final String KEY_FIELD_DAILY_REMINDER = "checkedDaily";


    public SharedPreferences sharedPreferences;
    public SharedPreferences.Editor editor;
    public AppCompactPreferences(Context context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.apply();
    }
    public void setReminderReleaseTime(String time){
        editor.putString(KEY_REMINDER_RELEASE,time);
        editor.commit();
    }
    public void setReminderReleaseMessage (String message){
        editor.putString(KEY_REMINDER_MESSAGE_Release,message);
    }
    public void setReminderDailyTime(String time){
        editor.putString(KEY_REMINDER_DAILY,time);
        editor.commit();
    }
    public void setReminderDailyMessage(String message){
        editor.putString(KEY_REMINDER_MESSAGE_Daily,message);
    }
}
