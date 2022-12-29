package com.example.CourseWork;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    NotificationManager notificationManager;

    private static final int NOTIFY_ID_BOOK = 228;

    private static final int NOTIFY_ID_CANCEL_BOOK = 1337;

    private static final String CHANNEL_ID_BOOK = "Booking channel";

    private static final String CHANNEL_ID_CANCEL_BOOK = "Cancel booking channel";

    static final private int CHOOSE_HOTEL = 0;
    static final private int CHOOSE_TIME_TRIP = 1;

    String date1;
    String date2;

    Intent intentHotel;
    Intent intentTimeTrip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        intentHotel = new Intent(this, Activity_hotel_choice.class);
        intentTimeTrip = new Intent(this, Activity_time_trip_choice.class);
    }

    @Override
    protected void onResume() {
        super.onResume();

        Button switchToHotel = findViewById(R.id.switchToHotelActivity);
        Button switchToTimeTrip = findViewById(R.id.switchToTimeTripActivity);

        switchToHotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(intentHotel, CHOOSE_HOTEL);
            }
        });

        switchToTimeTrip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(intentTimeTrip, CHOOSE_TIME_TRIP);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CHOOSE_HOTEL) {
            TextView infoTextView = findViewById(R.id.hotelName);
            if (resultCode == RESULT_OK) {
                infoTextView.setText(data.getStringExtra("hotel"));
            }else {
                infoTextView.setText(" "); // стираем текст
            }
        }
        else if (requestCode == CHOOSE_TIME_TRIP) {
            TextView infoTextView = findViewById(R.id.DateOfTrip);
            if (resultCode == RESULT_OK) {
                if (!data.getStringArrayExtra("time")[0].equals("")){
                    date1 = data.getStringArrayExtra("time")[0];
                    date2 = data.getStringArrayExtra("time")[1];
                    infoTextView.setText(date1 + "\n" + date2);
                }
            }else {
                infoTextView.setText(" ");
            }
        }
    }

    public void booking(View view){
        if (!((TextView)(findViewById(R.id.editTextTextPersonName))).getText().equals("") && !((TextView)findViewById(R.id.hotelName)).getText().equals(" ") && !date1.equals(" ")){
            notificationManager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
            Intent notificationIntent = new Intent(getApplicationContext(), MainActivity.class);
            NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID_BOOK)
                    .setAutoCancel(false)
                    .setSmallIcon(R.mipmap.ic_launcher_with_black_hand_foreground)
                    .setContentTitle("Уведомление о бронировании")
                    .setContentText("Было забронированл место")
                    .setStyle(new NotificationCompat.BigTextStyle().bigText("Было забронировано место в гостинице " + ((TextView)findViewById(R.id.hotelName)).getText() +
                            " на имя " + ((TextView)findViewById(R.id.editTextTextPersonName)).getText() + " c " + date1 + " по " + date2));
            createChannelIfNeeded(notificationManager, CHANNEL_ID_BOOK);
            notificationManager.notify(NOTIFY_ID_BOOK, notificationBuilder.build());
        }
        else{
            Toast.makeText(this, "Незаполненная форма!", Toast.LENGTH_LONG).show();
        }
    }

    public void cancelBooking(View view){
        if (!((TextView)(findViewById(R.id.editTextTextPersonName))).getText().equals("") && !((TextView)findViewById(R.id.hotelName)).getText().equals(" ") && !date1.equals(" ")){
            notificationManager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
            Intent notificationIntent = new Intent(getApplicationContext(), MainActivity.class);
            NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID_CANCEL_BOOK)
                    .setAutoCancel(false)
                    .setSmallIcon(R.mipmap.ic_launcher_with_black_hand_foreground)
                    .setContentTitle("Уведомление о бронировании")
                    .setContentText("Бронирование места отменено")
                    .setStyle(new NotificationCompat.BigTextStyle().bigText("Бронь места в гостинице " + ((TextView)findViewById(R.id.hotelName)).getText() +
                            " на имя " + ((TextView)findViewById(R.id.editTextTextPersonName)).getText() + " c " + date1 + " по " + date2 + " была отменена"));
            createChannelIfNeeded(notificationManager, CHANNEL_ID_CANCEL_BOOK);
            notificationManager.notify(NOTIFY_ID_CANCEL_BOOK, notificationBuilder.build());
        }
        else{
            Toast.makeText(this, "Незаполненная форма!", Toast.LENGTH_LONG).show();
        }
    }

    public static void createChannelIfNeeded(NotificationManager manager, String CHANNEL){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL, CHANNEL, NotificationManager.IMPORTANCE_DEFAULT);
            manager.createNotificationChannel(notificationChannel);
        }
    }
}