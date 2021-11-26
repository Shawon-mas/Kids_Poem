package com.kidspoem.kidspoem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;

public class NumberActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number);
    }
    @Override
    public void onBackPressed(){
        super.onBackPressed();
        Animatoo.animateSwipeRight(NumberActivity.this); //fire the slide left animation
    }
}