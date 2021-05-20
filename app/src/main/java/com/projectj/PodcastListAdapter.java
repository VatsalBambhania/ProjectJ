package com.projectj;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.projectj.Database.PodcastDetails;

import java.util.ArrayList;
import java.util.List;

public class PodcastListAdapter extends RecyclerView.Adapter<PodcastListAdapter.PodcastListViewHolder> {

//    public static DiffUtil.ItemCallback<PodcastDetails>  diffCallback = new DiffUtil.ItemCallback<PodcastDetails>() {
//        @Override
//        public boolean areItemsTheSame(@NonNull PodcastDetails oldItem, @NonNull PodcastDetails newItem) {
//            return oldItem.id == newItem.id;
//        }
//
//        @Override
//        public boolean areContentsTheSame(@NonNull PodcastDetails oldItem, @NonNull PodcastDetails newItem) {
//            return oldItem.name.equals(newItem.name)
//                    && oldItem.podcastBy.equals(newItem.podcastBy);
//        }
//    };

    List<PodcastDetails> list;

    public PodcastListAdapter() {
//        super(PodcastListAdapter.diffCallback);
        list = new ArrayList<>();
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
