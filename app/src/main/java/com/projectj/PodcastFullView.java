package com.projectj;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.projectj.Database.MemberViewModel;

import static com.projectj.MainActivity.isPlaying;
import static com.projectj.MainActivity.musicPlayer;

public class PodcastFullView extends AppCompatActivity {

    TextView name,by;
    MemberViewModel vm;
    public static final String PODCASTID = "Id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_podcast_full_view);
        int id = getIntent().getIntExtra(PODCASTID, -1);
        if(id!=-1){
            name = findViewById(R.id.fullPodcastNameTv);
            by = findViewById(R.id.fullPodcastByTv);
            vm = new ViewModelProvider(this).get(MemberViewModel.class);
        }
        else{
            Toast.makeText(this, "Cannot load details", Toast.LENGTH_SHORT).show();
        }

    }

    public void plpa(View view){
        if(isPlaying){
            musicPlayer.pause();
            view.setBackground(ContextCompat.getDrawable(this, R.drawable.ic_play));
            isPlaying = false;
        }
        else{
            musicPlayer.start();
            view.setBackground(ContextCompat.getDrawable(this, R.drawable.ic_pause));
            isPlaying = true;
        }
    }

    public void deletePodcast(View view){

    }

}