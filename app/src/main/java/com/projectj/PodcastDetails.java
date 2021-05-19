package com.projectj;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static com.projectj.MainActivity.PODCASTREF;

public class PodcastDetails {

    int id;
    String name;
    String duration;
    String podcastBy;

    public PodcastDetails(String duration, int id, String name, String podcastBy) {
        this.id = id;
        this.name = name;
        this.duration = duration;
        this.podcastBy = podcastBy;
    }

}
