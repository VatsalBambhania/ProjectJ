package com.projectj;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.projectj.Database.PodcastDetails;

import java.util.ArrayList;
import java.util.List;

import static com.projectj.MainActivity.PODCASTREF;


public class PodcastList extends Fragment {

    PodcastListAdapter adapter;
    List<PodcastDetails> podcastList = new ArrayList<>();
    RecyclerView podcastListRv;
    DataRepository repo;
    Thread th;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
//        if(!podcastList.isEmpty())
        return inflater.inflate(R.layout.fragment_podcast_list, container, false);
//        else
//            return inflater.inflate(R.layout.no_podcast_available, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        if(!podcastList.isEmpty()){
            podcastListRv = view.findViewById(R.id.podcastList);
            adapter = new PodcastListAdapter();
            podcastListRv.setLayoutManager(new LinearLayoutManager(requireContext()));
            podcastListRv.setAdapter(adapter);
            adapter.submitList(podcastList);
//        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

}
//        binding = null;
//        binding.buttonFirst.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                NavHostFragment.findNavController(PodcastList.this)
//                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
//            }
//        });
//        binding = FragmentPodcastListBinding.inflate(inflater, container, false);
//        return binding.getRoot();
//    private FragmentPodcastListBinding binding;
//import com.projectj.databinding.FragmentPodcastListBinding;
