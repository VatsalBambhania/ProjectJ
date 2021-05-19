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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.time.chrono.ThaiBuddhistEra;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.LongStream;

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

            new Thread(() -> {
                podcastList = getPodcast();
                adapter.submitList(podcastList);
            }).start();
//        }
    }

    List<PodcastDetails> getPodcast(){
        FirebaseDatabase db = FirebaseDatabase.getInstance("https://projectj-cd887-default-rtdb.asia-southeast1.firebasedatabase.app/");
        DatabaseReference ref = db.getReference(PODCASTREF);
        List<PodcastDetails> list = new ArrayList<>();
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Toast.makeText(requireContext(), "Count: " + dataSnapshot.getChildrenCount(), Toast.LENGTH_SHORT).show();
                for(DataSnapshot snapshot: dataSnapshot.getChildren()){
                    list.add(snapshot.getValue(PodcastDetails.class));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.i("Error", databaseError.getDetails());
                Toast.makeText(requireContext(), "Error fuck it", Toast.LENGTH_SHORT).show();
            }
        });
        return list;
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
