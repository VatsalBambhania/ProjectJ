package com.projectj;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.internal.InternalTokenProvider;

public class MainActivity extends AppCompatActivity {
    
    private MediaPlayer musicPlayer;
    Toolbar toolbar;
    FloatingActionButton addNewPodcast;
    TextView podcastNameTv;
    Button playPauseBt;
    boolean isPlaying = false;
    public static final int ADDNEWPODCAST = 1;
    public static final int ADDPODCASTSUCCESS = 2;
    public static final int ADDPODCASTFAILED = 3;

    public static final String PODCASTREF = "podcast_list";
    public static final String USERSREF = "users";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        podcastNameTv = findViewById(R.id.podcastNameTv);
        playPauseBt = findViewById(R.id.playPauseBt);
        addNewPodcast  = findViewById(R.id.addNewPodcast);
        
        addNewPodcast.setOnClickListener(view -> {
            Intent i = new Intent(this, AddPodcast.class);
            startActivityForResult(i, ADDNEWPODCAST);
        });
        
        musicPlayer = MediaPlayer.create(this, R.raw.uptown_funk);
        
        setSupportActionBar(toolbar);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.container, LoginFragment.class, null, "LoginFrag").commit();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == ADDPODCASTSUCCESS)
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

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logout) {
            FirebaseAuth.getInstance().signOut();
            Toast.makeText(this, "Sign Out", Toast.LENGTH_SHORT).show();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, LoginFragment.class, null, "LoginFrag").commit();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    
    public void changeStatus(View view){
        if(isPlaying){
            musicPlayer.pause();
            isPlaying = false;
        }
        else{
            musicPlayer.start();
            isPlaying = true;
        }
    }

}
//    @Override
//    public boolean onSupportNavigateUp() {
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
//        return NavigationUI.navigateUp(navController, appBarConfiguration)
//                || super.onSupportNavigateUp();
//    }


//        setSupportActionBar(binding.toolbar);

//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
//        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

//        binding.fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });