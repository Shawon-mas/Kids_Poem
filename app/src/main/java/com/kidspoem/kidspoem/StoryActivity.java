package com.kidspoem.kidspoem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.kidspoem.kidspoem.adapter.ClickItem;
import com.kidspoem.kidspoem.adapter.StoryAdapter;
import com.kidspoem.kidspoem.adapter.StoryTittle;
import com.kidspoem.kidspoem.adapter.VideoAdapter;
import com.kidspoem.kidspoem.adapter.VideoTittle;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;
import com.pluscubed.recyclerfastscroll.RecyclerFastScroller;

import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;

public class StoryActivity extends AppCompatActivity implements ClickItem {
    RecyclerView recyclerView;
    List<StoryTittle> storyTittles;
    StoryAdapter adapter;
    private YouTubePlayerView youTubePlayerView;
    private YouTubePlayer youTubePlayer;
    private String currentVideoId;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference mRef=db.collection("StoryList");
    private AdView adView_player;
    RecyclerFastScroller fastScroller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);

        recyclerView=findViewById(R.id.recycler_view_story_list);
        fastScroller=findViewById(R.id.scroll_bar);
        recyclerView.setHasFixedSize(true);
        storyTittles=new ArrayList<StoryTittle>();
        youTubePlayerView = findViewById(R.id.youtube_player_view_story);
        fastScroller.attachRecyclerView(recyclerView);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        adapter=new StoryAdapter(StoryActivity.this,storyTittles,this);
        recyclerView.setAdapter(adapter);
        mRef.orderBy("id", Query.Direction.ASCENDING).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if (error != null) {
                    Log.d("Firestore error", error.getMessage());
                    return;
                }
                for (DocumentChange documentChange : value.getDocumentChanges()) {
                    if (documentChange.getType() == DocumentChange.Type.ADDED) {
                        storyTittles.add(documentChange.getDocument().toObject(StoryTittle.class));
                    }
                    adapter.notifyDataSetChanged();
                }
            }
        });
        addshow();

    }

    private void addshow() {
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        adView_player = findViewById(R.id.adView_story);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView_player.loadAd(adRequest);
    }


    @Override
    public void onItemClick(int position) {

        cueVideo(storyTittles.get(position).getStoryId());

        youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer initializedYouTubePlayer) {
                youTubePlayer = initializedYouTubePlayer;
                youTubePlayer.cueVideo(currentVideoId, 0);

            }
        });
        Toasty.info(getApplicationContext(),"Please wait loading video",Toasty.LENGTH_SHORT,true).show();
    }

    void cueVideo(String videoId) {
        currentVideoId = videoId;

        if(youTubePlayer == null)
            return;

        youTubePlayer.cueVideo(videoId, 0);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        youTubePlayerView.release();
    }
}