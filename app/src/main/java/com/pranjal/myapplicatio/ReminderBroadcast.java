package com.pranjal.myapplicatio;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;

public class ReminderBroadcast extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationS _notificationUtils = new NotificationS(context);



NotificationS n = new NotificationS(context);
        NotificationCompat.Builder _builder = _notificationUtils.setNotification("Good Thoughts", "Your thought of the day is available now...").setContentIntent(n.pendingIntent);
        _notificationUtils.getManager().notify(101, _builder.build());

    }
}
