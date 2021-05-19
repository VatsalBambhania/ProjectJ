package com.projectj;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.projectj.Database.MemberViewModel;


public class PodcastList extends Fragment {

    PodcastListAdapter adapter;
    RecyclerView podcastListRv;
    MemberViewModel viewModel;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        View view = inflater.inflate(R.layout.fragment_podcast_list, container, false);
        viewModel = new ViewModelProvider(requireActivity()).get(MemberViewModel.class);
        Toast.makeText(requireContext(), "Count: " + viewModel.getCount(), Toast.LENGTH_SHORT).show();
        podcastListRv = view.findViewById(R.id.podcastList);
        adapter = new PodcastListAdapter();
        podcastListRv.setLayoutManager(new LinearLayoutManager(requireContext()));
        viewModel.getAllPodcast().observe(requireActivity(), list -> adapter.submitList(list));
        podcastListRv.setAdapter(adapter);
        return view;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

}
