package com.kidspoem.kidspoem.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.recyclerview.widget.RecyclerView;

import com.kidspoem.kidspoem.R;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.util.List;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.MyViewHolder> {
    Context context;
     List<VideoTittle> videoTittles;
    private final ClickItem clickItem;

    public VideoAdapter(Context context,List<VideoTittle> videoTittles, ClickItem clickItem) {
        this.context=context;
        this.videoTittles = videoTittles;
        this.clickItem = clickItem;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.video_list_item, parent, false);
        return new MyViewHolder(v);



        /*
         View v = LayoutInflater.from(parent.getContext()).inflate(video_list_item,parent,false);
         return new MyVIewHolder(v);
         */
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        VideoTittle videoTittle = videoTittles.get(position);

        holder.textView.setText(videoTittle.getTitle());


    }

    @Override
    public int getItemCount() {
        return videoTittles.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {


        TextView textView;
        ImageView imageViewPlay;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.vtitle_id);
            imageViewPlay=itemView.findViewById(R.id.play_video);




            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(clickItem!=null)
                    {

                        int pos=getAdapterPosition();
                        if (pos!=RecyclerView.NO_POSITION)
                        {
                            clickItem.onItemClick(pos);
                        }
                    }

                }
            });

        }

    }
}
/*
else {
                            imageViewPause.setVisibility(View.INVISIBLE);
                            imageViewPlay.setVisibility(View.VISIBLE);
                        }
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
 */




