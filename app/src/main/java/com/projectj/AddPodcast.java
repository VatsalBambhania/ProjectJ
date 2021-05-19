package com.projectj;

import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.EditText;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.projectj.databinding.ActivityAddPodcastBinding;

import static com.projectj.MainActivity.ADDPODCASTSUCCESS;

public class AddPodcast extends AppCompatActivity {

    EditText nameEt, durationEt, podcastByEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        nameEt = findViewById(R.id.nameEt);
        durationEt = findViewById(R.id.durationEt);
        podcastByEt = findViewById(R.id.podcastByEt);
    }

    public void addNewPodcast(View view){
        String name = nameEt.getText().toString().trim();
        String duration = durationEt.getText().toString().trim();
        String podcastBy = podcastByEt.getText().toString().trim();
        int id = 0;
//        PodcastDetails podcast = new PodcastDetails(id, name, duration, podcastBy);
        //TODO: Add new podcast to firebase database
        setResult(ADDPODCASTSUCCESS);
        finish();
    }

}