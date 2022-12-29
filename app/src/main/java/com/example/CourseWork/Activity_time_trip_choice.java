package com.example.CourseWork;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import java.security.PublicKey;

public class Activity_time_trip_choice extends AppCompatActivity {

    Date firstDate = new Date(); //Начало отдыха
    Date secondDate = new Date();  //Конец отдыха

    Intent intentHomeAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_trip_choice);
        intentHomeAnswer = new Intent(this, MainActivity.class);
    }

    @Override
    protected void onResume() {
        super.onResume();

        CalendarView calendarView = findViewById(R.id.calendarView); //Наш календарь
        TextView textStartTrip = findViewById(R.id.startTrip); //Строка вывода начала поездки
        TextView textEndTrip = findViewById(R.id.endTrip); //Строка вывода конца поездки

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            boolean setFirstDay = true;
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                if (setFirstDay){
                    firstDate.setDate(i,i1+1,i2);
                    textStartTrip.setText("Начало поездки: "+ firstDate.getDate());
                    setFirstDay = false;
                }
                else {
                    secondDate.setDate(i,i1+1,i2);
                    textEndTrip.setText("Конец поездки: " + secondDate.getDate());
                    setFirstDay = true;
                }

            }
        });
    }

    public void returnClick(View view){
        if (firstDate.compareTo(secondDate) > 0){
            intentHomeAnswer.putExtra("time", new String[]{firstDate.getDate(), secondDate.getDate()});
        }
        else{
            intentHomeAnswer.putExtra("time", new String[]{" ", " "});
        }
        setResult(RESULT_OK, intentHomeAnswer);
        finish();
    }
}