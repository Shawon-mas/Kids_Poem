package com.kidspoem.kidspoem.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kidspoem.kidspoem.R;

import java.util.List;

public class StoryAdapter extends RecyclerView.Adapter<StoryAdapter.MyViewHolder> {
    Context context;
     List<StoryTittle> storyTittles;
    private final ClickItem clickItem;

    public StoryAdapter(Context context,  List<StoryTittle> storyTittles, ClickItem clickItem) {
        this.context=context;
        this.storyTittles = storyTittles;
        this.clickItem = clickItem;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.video_list_item, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        StoryTittle storyTittle = storyTittles.get(position);

        holder.textView.setText(storyTittle.getTitle());


    }

    @Override
    public int getItemCount() {
        return storyTittles.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {


        TextView textView;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.vtitle_id);
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




