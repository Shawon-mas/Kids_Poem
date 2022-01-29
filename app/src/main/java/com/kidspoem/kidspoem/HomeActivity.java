package com.kidspoem.kidspoem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class HomeActivity extends AppCompatActivity {
    private AdView adView;
    CardView cardView_poem,cardView_stoty;
    float v=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        addShow();
        intiId();


    }

    private void addShow() {
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        adView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
    }

    private void intiId() {



        cardView_poem=findViewById(R.id.poem_cardview);
        cardView_poem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),PlayerActivity.class);
                startActivity(intent);
                Animatoo.animateSwipeLeft(HomeActivity.this);
            }
        });

        cardView_stoty=findViewById(R.id.story_cardview);
        cardView_stoty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),StoryActivity.class);
                startActivity(intent);
                Animatoo.animateSwipeLeft(HomeActivity.this);
            }
        });


        //element

        cardView_poem.setAlpha(v);
        cardView_stoty.setAlpha(v);
        //trans

        cardView_poem.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(400).start();
        cardView_stoty.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(400).start();
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
        Animatoo.animateSwipeRight(HomeActivity.this); //fire the slide left animation
    }
}