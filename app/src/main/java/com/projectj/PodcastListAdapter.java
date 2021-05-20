package com.projectj;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.projectj.Database.PodcastDetails;

import java.util.ArrayList;
import java.util.List;

import static com.projectj.PodcastFullView.PODCASTID;

public class PodcastListAdapter extends RecyclerView.Adapter<PodcastListAdapter.PodcastListViewHolder> {

    List<PodcastDetails> list;
    Context context;

    public PodcastListAdapter(Context context) {
        list = new ArrayList<>();
        this.context = context;
    }

    @NonNull
    @Override
    public PodcastListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PodcastListViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.podcast_item_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PodcastListViewHolder holder, int position) {
        PodcastDetails details = list.get(position);
        holder.nameTv.setText(details.name);
        holder.durationTv.setText(details.duration);
        holder.podcastByTv.setText(details.podcastBy);
        holder.itemView.setOnClickListener(view -> {
            Intent i = new Intent(context, PodcastFullView.class);
            i.putExtra(PODCASTID, details.getId());
            context.startActivity(i);
        });
        Log.i("Error", details.name);
    }

    public void setList(List<PodcastDetails> newList){
        list = newList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class PodcastListViewHolder extends RecyclerView.ViewHolder{

        TextView nameTv, durationTv, podcastByTv;
        Button playBt;

        public PodcastListViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTv = itemView.findViewById(R.id.name);
            durationTv = itemView.findViewById(R.id.duration);
            podcastByTv = itemView.findViewById(R.id.podcastBy);
            playBt = itemView.findViewById(R.id.playBt);
        }
    }

}
