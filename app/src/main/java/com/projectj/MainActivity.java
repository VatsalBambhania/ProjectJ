package com.projectj;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;


import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.internal.InternalTokenProvider;
import com.projectj.Database.PodcastDetails;

import static com.projectj.PodcastListAdapter.currentId;
import static com.projectj.PodcastListFragment.adapter;

public class MainActivity extends AppCompatActivity {

    public static MediaPlayer musicPlayer;
    Toolbar toolbar;
    FloatingActionButton addNewPodcast;
    static TextView podcastNameTv;
    public static Button playPauseBt;
    public static boolean isPlaying = false;
    public static int lastPlayed = -1;
    public static final int ADDNEWPODCAST = 1;
    public static final int ADDPODCASTSUCCESS = 2;
    public static final int ADDPODCASTFAILED = 3;

    public static final String PODCASTREF = "podcast_list";
    public static final String USERSREF = "users";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.container, PodcastListFragment.class, null, "PodcastList")
                .commit();
        Toast.makeText(this, "MainActivity", Toast.LENGTH_SHORT).show();
        toolbar = findViewById(R.id.toolbar);
        podcastNameTv = findViewById(R.id.podcastNameTv);
        playPauseBt = findViewById(R.id.playPauseBt);
        addNewPodcast = findViewById(R.id.addNewPodcast);

        addNewPodcast.setOnClickListener(view -> {
            Intent i = new Intent(this, AddPodcast.class);
            startActivityForResult(i, ADDNEWPODCAST);
        });


        setSupportActionBar(toolbar);
        musicPlayer = MediaPlayer.create(this, R.raw.podcast1);

    }

    @Override
    protected void onResume() {
        super.onResume();

        if(lastPlayed!=-1)
            setPodcastName();


        if(isPlaying){
            Toast.makeText(this, "Yup playing", Toast.LENGTH_SHORT).show();
            playPauseBt.setBackground(ContextCompat.getDrawable(this, R.drawable.ic_pause));
        }else{
            playPauseBt.setBackground(ContextCompat.getDrawable(this, R.drawable.ic_play));
        }
    }

    public static void setPodcastName(){
        for (PodcastDetails d: adapter.list){
            if(d.id==lastPlayed){
                podcastNameTv.setText(d.name);
                break;
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ADDPODCASTSUCCESS)
            Toast.makeText(this, "New Podcast Added", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "Failed to add Podcast", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

//        noinspection SimplifiableIfStatement
        if (id == R.id.action_logout) {
            FirebaseAuth.getInstance().signOut();
            Toast.makeText(this, "Sign Out", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(this, LoginActivity.class);
            i.setFlags(i.getFlags() | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);
            finish();
            return true;
        } else if (id == R.id.profile) {
            Intent i = new Intent(this, UserProfile.class);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }

    public void changeStatus(View view) {
        if (isPlaying) {
            musicPlayer.pause();
            view.setBackground(ContextCompat.getDrawable(this, R.drawable.ic_play));
            isPlaying = false;
            lastPlayed = currentId;
            adapter.notifyDataSetChanged();
            currentId = -1;
            setPodcastName();
        } else {
            musicPlayer.start();
            lastPlayed = adapter.list.get(0).id;
            currentId = lastPlayed;
            adapter.notifyDataSetChanged();
            view.setBackground(ContextCompat.getDrawable(this, R.drawable.ic_pause));
            isPlaying = true;
            setPodcastName();
        }
    }

}