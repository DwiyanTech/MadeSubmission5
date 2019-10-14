package com.dwiyan.tvandmoviecatalogue;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.dwiyan.tvandmoviecatalogue.ApiConfig.ApiConfig;
import com.dwiyan.tvandmoviecatalogue.MovieCatalog.MovieApiInterface;
import com.dwiyan.tvandmoviecatalogue.MovieCatalog.MovieData;
import com.dwiyan.tvandmoviecatalogue.MovieCatalog.MovieResponse;
import com.dwiyan.tvandmoviecatalogue.Notification.AlarmReleaseReceiver;
import com.dwiyan.tvandmoviecatalogue.Notification.AppCompactPreferences;
import com.dwiyan.tvandmoviecatalogue.Notification.AlarmDailyReceiver;
import com.dwiyan.tvandmoviecatalogue.supportConfig.DateConfig;

import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SettingActivity extends AppCompatActivity {
    private TextView languange_setting;
    private Switch switcherDaily,switcherRelease;
    private AlarmDailyReceiver movieDailyReceiver;
    public SharedPreferences sReleaseReminder, sDailyReminder;
    public SharedPreferences.Editor editorReleaseReminder, editorDailyReminder;
    private AppCompactPreferences appCompactPreferences;
    private  AlarmReleaseReceiver alarmReleaseReceiver;
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        switcherDaily = findViewById(R.id.daily_reminder);

        switcherRelease = findViewById(R.id.release_Reminder);
        languange_setting = findViewById(R.id.local_setting);
        movieDailyReceiver = new AlarmDailyReceiver();
        appCompactPreferences = new AppCompactPreferences(this);

        setPreference();
        /* Release Switcher */
        switcherRelease.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean checkerReleaseSwitch) {
                editorReleaseReminder = sReleaseReminder.edit();
                        if(checkerReleaseSwitch){
                            editorReleaseReminder.putBoolean(AppCompactPreferences.KEY_FIELD_RELEASE_REMINDER,true);
                            editorReleaseReminder.apply();
                            String msg = getResources().getString(R.string.message_release_reminder);
                            alarmReleaseReceiver = new AlarmReleaseReceiver();
                           releaseReminderTrue();

                        } else {
                        editorReleaseReminder.putBoolean(AppCompactPreferences.KEY_FIELD_RELEASE_REMINDER,false);
                        editorReleaseReminder.apply();
                            alarmReleaseReceiver = new AlarmReleaseReceiver();
                            alarmReleaseReceiver.cancelAlarm(SettingActivity.this);
                        }
            }
        });
            /*    Daily Switcher   */
       switcherDaily.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
           @Override
           public void onCheckedChanged(CompoundButton compoundButton, boolean checkSwitch) {
               editorDailyReminder = sDailyReminder.edit();
               if(checkSwitch){
                   editorDailyReminder.putBoolean(AppCompactPreferences.KEY_FIELD_DAILY_REMINDER,true);
                    editorDailyReminder.apply();
                   dailyReminderTrue();
               } else {
                    editorDailyReminder.putBoolean(AppCompactPreferences.KEY_FIELD_DAILY_REMINDER,false);
                    editorDailyReminder.commit();
                    movieDailyReceiver.cancelAlarm(SettingActivity.this);


               }
           }
       });


        languange_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
                startActivity(mIntent);
            }});

    }



    public void dailyReminderTrue(){
        movieDailyReceiver  = new AlarmDailyReceiver();
        String time = "07:00";
        String message = getResources().getString(R.string.message_daily_reminder);
        movieDailyReceiver.setDailyAlarm(this,AlarmDailyReceiver.TYPE_REPEATING_DAILY,time,message);

    }
    public void releaseReminderTrue(){

        String time = "08:00";
        String title = getResources().getString(R.string.title_date_of_release);
        alarmReleaseReceiver.setReleaseAlarm(SettingActivity.this,AlarmReleaseReceiver.ALARM_TYPE_RELEASE,title,time);


    }

    private void setPreference() {
        sReleaseReminder = getSharedPreferences(AppCompactPreferences.KEY_HEADER_RELEASE_REMINDER, MODE_PRIVATE);
        sDailyReminder = getSharedPreferences(AppCompactPreferences.KEY_HEADER_DAILY_REMINDER, MODE_PRIVATE);
        boolean checkUpcomingReminder = sReleaseReminder.getBoolean(AppCompactPreferences.KEY_FIELD_RELEASE_REMINDER, false);
        switcherRelease.setChecked(checkUpcomingReminder);
        boolean checkDailyReminder = sDailyReminder.getBoolean(AppCompactPreferences.KEY_FIELD_DAILY_REMINDER, false);
       switcherDaily.setChecked(checkDailyReminder);
    }

}
