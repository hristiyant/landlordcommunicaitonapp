package venkov.vladimir.thebeginning.Views.notifications;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;

import java.sql.Timestamp;
import java.util.Calendar;

import venkov.vladimir.thebeginning.R;

public class ScheduleNotificationActivity extends Activity {

    Timestamp timestamp;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_notification);



        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.SECOND, 5);

//        timestamp.getTime()

        //alarmService
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        int f = 5;
        Intent intent = new Intent(".action.DISPLAY_NOTIFICATION");
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 100,
                intent, PendingIntent.FLAG_UPDATE_CURRENT);
//        alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), sendBroadcast());

        sendBroadcast(intent);

        finish();
    }
}
