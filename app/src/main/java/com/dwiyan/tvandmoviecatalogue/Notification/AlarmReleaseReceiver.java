package com.dwiyan.tvandmoviecatalogue.Notification;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.widget.Toast;


import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

import com.dwiyan.tvandmoviecatalogue.ApiConfig.ApiConfig;
import com.dwiyan.tvandmoviecatalogue.BuildConfig;
import com.dwiyan.tvandmoviecatalogue.MovieCatalog.MovieApiInterface;
import com.dwiyan.tvandmoviecatalogue.MovieCatalog.MovieData;
import com.dwiyan.tvandmoviecatalogue.MovieCatalog.MovieResponse;
import com.dwiyan.tvandmoviecatalogue.R;
import com.dwiyan.tvandmoviecatalogue.supportConfig.DateConfig;

import java.util.Calendar;
import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AlarmReleaseReceiver extends BroadcastReceiver {

    public static final String ALARM_TYPE_RELEASE = "typenya";
    public static final String ALARM_MESSAGE = "message_release";
    public static final String ALARM_RELEASE_TITLE = "Today Release !!!";
    private final static int ID_REPEATING = 103;
    private AlarmManager alarmManager;
    @Override
    public void onReceive(final Context context, Intent intent) {

        final MovieApiInterface movieApiInterface = ApiConfig.getApiWeb().create(MovieApiInterface.class);

        Call<MovieResponse> call = movieApiInterface.getMovieByDateRelease(BuildConfig.TMDB_API_KEY,DateConfig.DateConfigFormat(),DateConfig.DateConfigFormat());

        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                final List<MovieData> movieData = response.body().getResults();

                for (int index = 0; index < movieData.size(); index++) {

                    String title = movieData.get(index).getTitle();
                     String message = movieData.get(index).getOverview();
                    showAlarmNotification(context, title, message, ID_REPEATING);

                }


            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                String msg = context.getResources().getString(R.string.message_error_alarm_connection);
                String title = context.getResources().getString(R.string.error_koneksi);
                showAlarmNotification(context,title,msg,ID_REPEATING);
            }
        });
     // Toast.makeText(context,"Astaghfirullah",Toast.LENGTH_LONG).show();
    }

    public void setReleaseAlarm(Context context,String type,String message ,String time){
        alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, AlarmReleaseReceiver.class);
        intent.putExtra(ALARM_MESSAGE,message);
        intent.putExtra(ALARM_TYPE_RELEASE,type);
        Calendar calendar = Calendar.getInstance();

        String timeSplit[] = time.split(":");
        calendar.set(Calendar.HOUR_OF_DAY,Integer.parseInt(timeSplit[0])); // Release Receiver Di Set Jam 8
        calendar.set(Calendar.MINUTE,Integer.parseInt(timeSplit[1])); // Menset Untuk menit
        calendar.set(Calendar.SECOND,0); // Menset untuk Detik


             PendingIntent pendingIntent = PendingIntent.getBroadcast(context,ID_REPEATING,intent,PendingIntent.FLAG_UPDATE_CURRENT);
             if(calendar.before(Calendar.getInstance())) calendar.add(Calendar.DATE,1); //Cek Kalender JIKA TIDAK DIHARI ITU
             alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);


        Toast.makeText(context,"Release On",Toast.LENGTH_LONG).show();

    }
    public void cancelAlarm(Context context) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, AlarmDailyReceiver.class);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, ID_REPEATING, intent, 0);
        pendingIntent.cancel();

        if (alarmManager != null) {
            alarmManager.cancel(pendingIntent);
        }

        Toast.makeText(context, "Release Off", Toast.LENGTH_SHORT).show();
    }

    private void showAlarmNotification(Context context, String title, String message, int notifId) {
        String CHANNEL_ID = "Channel_2";
        String CHANNEL_NAME = "ALARM MANAGER";

        NotificationManager notificationManagerCompat = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_alarm_drawable_dpan)
                .setContentTitle(title)
                .setContentText(message)
                .setColor(ContextCompat.getColor(context, android.R.color.transparent))
                .setVibrate(new long[]{1000, 1000, 1000, 1000, 1000})
                .setSound(alarmSound);

        /*
        Untuk android Oreo ke atas perlu menambahkan notification channel
        Materi ini akan dibahas lebih lanjut di modul extended
         */
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            /* Create or update. */
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID,
                    CHANNEL_NAME,
                    NotificationManager.IMPORTANCE_DEFAULT);

            channel.enableVibration(true);
            channel.setVibrationPattern(new long[]{1000, 1000, 1000, 1000, 1000});

            builder.setChannelId(CHANNEL_ID);

            if (notificationManagerCompat != null) {
                notificationManagerCompat.createNotificationChannel(channel);
            }
        }

        Notification notification = builder.build();

        if (notificationManagerCompat != null) {
            notificationManagerCompat.notify(notifId, notification);
        }

    }
}
