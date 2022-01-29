package com.kidspoem.kidspoem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.danikula.videocache.HttpProxyCacheServer;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.kidspoem.kidspoem.adapter.ClickItem;
import com.kidspoem.kidspoem.adapter.VideoAdapter;
import com.kidspoem.kidspoem.adapter.VideoTittle;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;

public class PlayerActivity extends AppCompatActivity implements ClickItem {
RecyclerView recyclerView;
    List<VideoTittle> videoTittles;
    VideoAdapter adapter;
    private YouTubePlayerView youTubePlayerView;
    private YouTubePlayer youTubePlayer;
    private String currentVideoId;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference mRef=db.collection("VideoList");
    private AdView adView_player;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        recyclerView=findViewById(R.id.recycler_view_video_list);

         recyclerView.setHasFixedSize(true);
          videoTittles=new ArrayList<VideoTittle>();
         youTubePlayerView = findViewById(R.id.youtube_player_view);


        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        adapter=new VideoAdapter(PlayerActivity.this,videoTittles,this);
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
                        videoTittles.add(documentChange.getDocument().toObject(VideoTittle.class));
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

        adView_player = findViewById(R.id.adView_layer);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView_player.loadAd(adRequest);
    }


    @Override
    public void onItemClick(int position) {
        
        cueVideo(videoTittles.get(position).getVideoId());

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

/*
 youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                @Override
                public void onReady(@NonNull YouTubePlayer initializedYouTubePlayer) {
                    youTubePlayer = initializedYouTubePlayer;
                    youTubePlayer.cueVideo(currentVideoId, 0);
                }
            });

            }
        void cueVideo(String videoId) {
            currentVideoId = videoId;

            if(youTubePlayer == null)
                return;

            youTubePlayer.cueVideo(videoId, 0);
        }

  holder.cueVideo(videoTittle.getVideoId());

  hamdTitleArrayList.get(position).getHamdNo()
 */

