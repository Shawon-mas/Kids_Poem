package com.kidspoem.kidspoem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.kidspoem.kidspoem.adapter.PoemlistAdapter;
import com.kidspoem.kidspoem.adapter.Poemtitle;

public class PoemActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private DocumentReference mRef=db.collection("Poem").document();
    private PoemlistAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poem);
        intid();
        showdata();
    }

    private void showdata() {
        Query query = FirebaseFirestore.getInstance()
                .collection("Poem")
                .orderBy("title")
                .limit(50);
        FirestoreRecyclerOptions<Poemtitle> options= new FirestoreRecyclerOptions.Builder<Poemtitle>()
                .setQuery(query,Poemtitle.class).build();

        adapter=new PoemlistAdapter(options);
        GridLayoutManager gridLayoutManager=new GridLayoutManager(getApplicationContext(),2,GridLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(adapter);

    }

    private void intid() {
        recyclerView=findViewById(R.id.poemlist_recyclerview);


    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
        Animatoo.animateSwipeRight(PoemActivity.this); //fire the slide left animation
    }
    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }
    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}