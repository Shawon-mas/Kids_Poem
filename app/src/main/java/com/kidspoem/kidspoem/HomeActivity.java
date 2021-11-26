package com.kidspoem.kidspoem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;

public class HomeActivity extends AppCompatActivity {
    CardView cardView_numbers,cardView_letters,cardView_poem,cardView_shapes,cardView_reading,cardView_stoty;
    float v=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        intiId();

    }

    private void intiId() {
        cardView_numbers=findViewById(R.id.number_cardview);
        cardView_numbers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Working",Toast.LENGTH_LONG).show();
                Intent intent=new Intent(getApplicationContext(),NumberActivity.class);
                startActivity(intent);
                Animatoo.animateSwipeLeft(HomeActivity.this);

            }
        });

        cardView_letters=findViewById(R.id.letters_cardview);
        cardView_letters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        cardView_poem=findViewById(R.id.poem_cardview);
        cardView_poem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),PoemActivity.class);
                startActivity(intent);
                Animatoo.animateSwipeLeft(HomeActivity.this);
            }
        });
        cardView_shapes=findViewById(R.id.shape_cardview);
        cardView_reading=findViewById(R.id.reading_cardview);
        cardView_stoty=findViewById(R.id.story_cardview);


        //element
        cardView_numbers.setAlpha(v);
        cardView_letters.setAlpha(v);
        cardView_poem.setAlpha(v);
        cardView_shapes.setAlpha(v);
        cardView_reading.setAlpha(v);
        cardView_stoty.setAlpha(v);
        //trans
        cardView_numbers.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(400).start();
        cardView_letters.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(400).start();
        cardView_poem.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(400).start();
        cardView_shapes.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(400).start();
        cardView_reading.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(400).start();
        cardView_stoty.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(400).start();
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
        Animatoo.animateSwipeRight(HomeActivity.this); //fire the slide left animation
    }
}