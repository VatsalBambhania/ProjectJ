package com.projectj.Database;


import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;


import java.util.List;

public class MemberViewModel extends AndroidViewModel {

    private DataRepository repository;
    private LiveData<List<PodcastDetails>> detailsList;

    public MemberViewModel(@NonNull Application application) {
        super(application);
        repository = new DataRepository(application);
        detailsList = repository.getAllPodcast();
    }

    public LiveData<List<PodcastDetails>> getAllPodcast(){
        return repository.getAllPodcast();
    }

    public int getCount(){
        return repository.getCount();
    }

    public void addPodcast(PodcastDetails details){
        repository.addPodcast(details);
    }

    public void removePodcast(PodcastDetails details){
        repository.removePodcast(details);
    }

    public void updatePodcast(PodcastDetails details){
        repository.updatePodcast(details);
    }

    public PodcastDetails getPodcast(int id){
        return repository.getPodcast(id);
    }

}
