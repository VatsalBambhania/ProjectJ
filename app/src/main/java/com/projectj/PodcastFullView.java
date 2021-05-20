package com.projectj;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.projectj.Database.MemberViewModel;
import com.projectj.Database.PodcastDetails;

import java.util.Random;

import static com.projectj.MainActivity.isPlaying;
import static com.projectj.MainActivity.musicPlayer;
import static com.projectj.PodcastListAdapter.currentId;
import static com.projectj.PodcastListFragment.adapter;

public class PodcastFullView extends AppCompatActivity {

    TextView name,by, dur;
    MemberViewModel vm;
    Button ppBt;
    public static final String PODCASTID = "Id";
    PodcastDetails p;
    Random r;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_podcast_full_view);
        id = getIntent().getIntExtra(PODCASTID, -1);
        if(id!=-1){
            name = findViewById(R.id.fullPodcastNameTv);
            by = findViewById(R.id.fullPodcastByTv);
            dur = findViewById(R.id.fullDurationTv);
            ppBt = findViewById(R.id.playPauseButton);
            vm = new ViewModelProvider(this).get(MemberViewModel.class);
            p = vm.getPodcast(id);
            name.setText(p.name);
            by.setText(p.podcastBy);
            dur.setText(p.duration);
            r = new Random();
            Toast.makeText(this, "Ids " + id + " cid " + currentId, Toast.LENGTH_SHORT).show();
            if(id == currentId){
                ppBt.setBackground(ContextCompat.getDrawable(this,R.drawable.ic_pause));
            }
        }
        else{
            Toast.makeText(this, "Cannot load details", Toast.LENGTH_SHORT).show();
        }

    }

    public void plpa(View view){
        if(p!=null) {
            int[] pod = {
                    R.raw.podcast1,
                    R.raw.podcast2,
                    R.raw.podcast3
            };

            if (isPlaying) {
                musicPlayer.pause();
                view.setBackground(ContextCompat.getDrawable(this, R.drawable.ic_play));
                isPlaying = false;
                MainActivity.lastPlayed = currentId;
                currentId = -1;
                adapter.notifyDataSetChanged();
            } else {
                int rand = r.nextInt(3);
                currentId = id;
                adapter.notifyDataSetChanged();
                Toast.makeText(this, "Playing " + p.name, Toast.LENGTH_SHORT).show();
                musicPlayer = MediaPlayer.create(this, pod[rand]);
                musicPlayer.start();
                view.setBackground(ContextCompat.getDrawable(this, R.drawable.ic_pause));
                isPlaying = true;
            }
        }
    }

    public void deletePodcast(View view){
        if(p!=null){
            vm.removePodcast(p);
            finish();
        }
    }

}