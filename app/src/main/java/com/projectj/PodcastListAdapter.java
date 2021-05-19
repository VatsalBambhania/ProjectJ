package com.projectj;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class PodcastListAdapter extends ListAdapter<PodcastDetails, PodcastListAdapter.podcastListViewHolder> {

    public static DiffUtil.ItemCallback<PodcastDetails>  diffCallback = new DiffUtil.ItemCallback<PodcastDetails>() {
        @Override
        public boolean areItemsTheSame(@NonNull PodcastDetails oldItem, @NonNull PodcastDetails newItem) {
            return oldItem.id == newItem.id;
        }

        @Override
        public boolean areContentsTheSame(@NonNull PodcastDetails oldItem, @NonNull PodcastDetails newItem) {
            return oldItem.duration.equals(newItem.duration)
                    && oldItem.name.equals(newItem.name)
                    && oldItem.podcastBy.equals(newItem.podcastBy);
        }
    };

    public PodcastListAdapter() {
        super(PodcastListAdapter.diffCallback);
    }

    @NonNull
    @Override
    public podcastListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new podcastListViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.podcast_item_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull podcastListViewHolder holder, int position) {
        PodcastDetails details = getItem(position);
        holder.nameTv.setText(details.name);
        holder.durationTv.setText(details.duration);
        holder.podcastByTv.setText(details.podcastBy);
    }

    public static class podcastListViewHolder extends RecyclerView.ViewHolder{

        TextView nameTv, durationTv, podcastByTv;
        Button playBt;

        public podcastListViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTv = itemView.findViewById(R.id.name);
            durationTv = itemView.findViewById(R.id.duration);
            podcastByTv = itemView.findViewById(R.id.podcastBy);
            playBt = itemView.findViewById(R.id.playBt);
        }
    }

}
