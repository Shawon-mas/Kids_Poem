package com.kidspoem.kidspoem.adapter;

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

public class PoemlistAdapter extends FirestoreRecyclerAdapter<Poemtitle, PoemlistAdapter.MyHolder> {

    private ArrayList<Poemtitle> poemtitles;

    public PoemlistAdapter(@NonNull FirestoreRecyclerOptions options, ArrayList<Poemtitle> poemtitles) {
        super(options);
        this.poemtitles = poemtitles;
    }

    public PoemlistAdapter(@NonNull FirestoreRecyclerOptions<Poemtitle> options) {
        super(options);
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.poemlist,parent,false);
        return new MyHolder(v);
    }

    @Override
    protected void onBindViewHolder(@NonNull MyHolder holder, int position, @NonNull Poemtitle model) {
        Glide.with(holder.imageView.getContext()).load(model.getImage()).into(holder.imageView);
        holder.textView.setText(model.getTitle());

    }

    public class MyHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.poem_thumbnils);
            textView=itemView.findViewById(R.id.poem_title);
        }
    }
}
