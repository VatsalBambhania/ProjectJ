package com.projectj;

import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.projectj.Database.MemberViewModel;
import com.projectj.Database.PodcastDetails;
import com.projectj.databinding.ActivityAddPodcastBinding;

import static com.projectj.MainActivity.ADDPODCASTSUCCESS;

public class AddPodcast extends AppCompatActivity {

    EditText nameEt, durationEt, podcastByEt;
    MemberViewModel vm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_podcast);
        nameEt = findViewById(R.id.nameEt);
        durationEt = findViewById(R.id.durationEt);
        podcastByEt = findViewById(R.id.podcastByEt);
        vm = new ViewModelProvider(this).get(MemberViewModel.class);
    }

    public void addNewPodcast(View view){
        String name = nameEt.getText().toString().trim();
        String duration = durationEt.getText().toString().trim();
        String podcastBy = podcastByEt.getText().toString().trim();
        PodcastDetails podcast = new PodcastDetails(name, duration, podcastBy);
        if(!name.isEmpty() && !podcastBy.isEmpty() && !duration.isEmpty()){
            vm.addPodcast(podcast);
            setResult(ADDPODCASTSUCCESS);
            finish();
        }else{
            Toast.makeText(this, "Please fill all the details", Toast.LENGTH_SHORT).show();
        }
    }

}