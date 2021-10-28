package com.example.sticky_notes;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

public class AppWidget extends AppWidgetProvider {

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for(int appWidgetId:appWidgetIds){
            Intent launchIntent=new Intent(context,MainActivity.class);
            PendingIntent pendingIntent=PendingIntent.getActivity(context,0,launchIntent,0);
            RemoteViews remoteViews=new RemoteViews(context.getPackageName(),R.layout.widget_layout);
            remoteViews.setOnClickPendingIntent(R.id.idTVWidget,pendingIntent);
            appWidgetManager.updateAppWidget(appWidgetId,remoteViews);
        }
    }
}
