package com.kidspoem.kidspoem.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.kidspoem.kidspoem.R;

import java.util.ArrayList;
import java.util.List;

public class PoemlistAdapter extends RecyclerView.Adapter<PoemlistAdapter.MyVIewHolder>  {
    private List<Poemtitle> poemtitles;
    Context context;

    public PoemlistAdapter(List<Poemtitle> poemtitles) {
        this.poemtitles = poemtitles;
    }

    @NonNull
    @Override
    public MyVIewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context=parent.getContext();
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.poemlist,parent,false);
         return new MyVIewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyVIewHolder holder, int position) {
             Poemtitle poemtitle=poemtitles.get(position);
             holder.setImageView(poemtitles.get(position));


    }

    @Override
    public int getItemCount() {
        return poemtitles.size();
    }

    class MyVIewHolder extends RecyclerView.ViewHolder
    {
      private ImageView imageView;
      private TextView textView;
        public MyVIewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.viewpager_image);

        }
        void setImageView(Poemtitle poemtitle)
        {
            Glide.with(context).load(poemtitle.getImage()).override(300,300).into(imageView);
        }
    }



}
// Glide.with(holder.imageView.getContext()).load(model.getImage()).into(holder.imageView);
//        holder.textView.setText(model.getTitle());
//        imageView=itemView.findViewById(R.id.viewpager_image);
//            textView=itemView.findViewById(R.id.poem_title);
//            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.poemlist,parent,false);
     //   return new MyHolder(v);
//   private ArrayList<Poemtitle> poemtitles;