/*
 * ***************************************************************************************************************
 * Plan2Cook
 * Copyright (c) 2020
 *  Rhys Evans
 * STU54259 - Arden University
 * ***************************************************************************************************************
 */

package com.stu54259.plan2cook;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.Settings;
import android.text.InputType;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.stu54259.plan2cook.Adapters.NotificationPublisher;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static androidx.core.app.NotificationCompat.PRIORITY_HIGH;

/**
 * All this code is created by Rhys Evans, STU54259.
 */
public class Reminders extends MainActivity {
    public static final String NOTIFICATION_CHANNEL_ID = "10001";
    final static int req1 = 1;
    private final static String default_notification_channel_id = "default";
    private static final int RQS_PICK_CONTACT = 0;
    public String a = "0";
    EditText eText;
    EditText reminder;
    DatePickerDialog picker;


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminders);
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        Intent a = new Intent(Reminders.this, MainActivity.class);
                        startActivity(a);
                        break;
                    case R.id.recipes:
                        Intent b = new Intent(Reminders.this, RecipeSearch.class);
                        startActivity(b);
                        break;
                    case R.id.shoppingList:
                        Intent c = new Intent(Reminders.this, ShoppingList.class);
                        startActivity(c);
                        break;
                    case R.id.mealPlan:
                        Intent d = new Intent(Reminders.this, MenuPlan.class);
                        startActivity(d);
                        break;
                    case R.id.reminder:
                        Intent e = new Intent(Reminders.this, Reminders.class);
                        startActivity(e);
                        break;
                }
                return false;
            }
        });


        reminder = findViewById(R.id.reminderText);
        eText = findViewById(R.id.reminderDate);
        eText.setInputType(InputType.TYPE_NULL);
        eText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                final int day = cldr.get(Calendar.DAY_OF_MONTH);
                final int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(Reminders.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @SuppressLint("SetTextI18n")
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                eText.setText(year + "/" + (monthOfYear + 1) + "/" + dayOfMonth);
                            }
                        }, year, month, day);
                picker.show();
            }

        });
    }

    /**
     * All this code is created by Rhys Evans, STU54259.
     */
    private void scheduleNotification(Notification notification,
                                      long delay) {
        Intent notificationIntent = new Intent(Reminders.this, NotificationPublisher.class);
        notificationIntent.putExtra(NotificationPublisher.NOTIFICATION_ID, 1);
        notificationIntent.putExtra(NotificationPublisher.NOTIFICATION, notification);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, notificationIntent, 0);
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, delay, pendingIntent);
    }

    private Notification getNotification(String content) {
        String description = reminder.getText().toString();
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID);
        builder.setContentTitle("Reminder Service");
        builder.setContentText(description);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setStyle(new NotificationCompat.BigTextStyle());
        builder.setVibrate(new long[]{1000, 1000})
                .setSound(Settings.System.DEFAULT_NOTIFICATION_URI)
                .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                .setLights(Color.RED, 500, 500);
        builder.setAutoCancel(true);
        builder.setPriority(PRIORITY_HIGH);
        builder.setChannelId(NOTIFICATION_CHANNEL_ID);
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        Context context;
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
        builder.setContentIntent(pendingIntent);
        return builder.build();
    }

    /**
     * All this code is created by Rhys Evans, STU54259.
     */

    public void saveReminder(View view) {
        String myFormat = "yyyy/MM/dd";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.getDefault());
        String date = eText.getText().toString();
        Date date1 = null;
        long milli = 0;
        try {
            date1 = sdf.parse(date);
            milli = date1.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Log.d("date", date);
        // Set date at 7AM
        long delay = (milli + 25200000);
        scheduleNotification(getNotification(reminder.getText().toString()), delay);

        Intent intentHome = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intentHome);
    }
}
