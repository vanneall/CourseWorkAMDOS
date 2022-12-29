package com.example.CourseWork;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Activity_hotel_choice extends AppCompatActivity {

    Intent intentHomeAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_choice);
        intentHomeAnswer = new Intent(this, MainActivity.class);
    }

    public void selectHolidayInn(View view){
        intentHomeAnswer.putExtra("hotel", ((TextView)findViewById(R.id.HolidayInn)).getText());
        setResult(RESULT_OK, intentHomeAnswer);
        finish();
    }

    public void selectBetaIzmailovo(View view){
        intentHomeAnswer.putExtra("hotel", ((TextView)findViewById(R.id.BetaIzmailovo)).getText());
        setResult(RESULT_OK, intentHomeAnswer);
        finish();
    }

    public void selectGammaIzmailovo(View view){
        intentHomeAnswer.putExtra("hotel", ((TextView)findViewById(R.id.GammaIzmailovo)).getText());
        setResult(RESULT_OK, intentHomeAnswer);
        finish();
    }

    public void selectCosmosMoscow(View view){
        intentHomeAnswer.putExtra("hotel", ((TextView)findViewById(R.id.CosmosMoscow)).getText());
        setResult(RESULT_OK, intentHomeAnswer);
        finish();
    }

    public void selectMarriotMoscow(View view){
        intentHomeAnswer.putExtra("hotel", ((TextView)findViewById(R.id.MarriotMoscow)).getText());
        setResult(RESULT_OK, intentHomeAnswer);
        finish();
    }

    public void selectNovotel(View view){
        intentHomeAnswer.putExtra("hotel", ((TextView)findViewById(R.id.Novotel)).getText());
        setResult(RESULT_OK, intentHomeAnswer);
        finish();
    }

    public void selectNacional(View view){
        intentHomeAnswer.putExtra("hotel", ((TextView)findViewById(R.id.Nacional)).getText());
        setResult(RESULT_OK, intentHomeAnswer);
        finish();
    }
}