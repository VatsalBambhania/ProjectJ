package com.projectj.Database;


import android.content.Context;

import androidx.lifecycle.LiveData;

import com.projectj.AddPodcast;

import java.util.List;

public class DataRepository {

    private final AppDao dao;
    private final LiveData<List<PodcastDetails>> membersList;

    public DataRepository(final Context context){

        AppDatabase database = AppDatabase.getInstance(context);
        dao = database.dao();
        membersList = dao.getAllPodcast();

    }

    public void addPodcast(PodcastDetails details){
        dao.AddPodcast(details);
    }

    public void removePodcast(PodcastDetails details){
        dao.RemovePodcast(details);
    }

    public void updatePodcast(PodcastDetails details){
        dao.UpdatePodcast(details);
    }

    public PodcastDetails getPodcast(int id){
        return dao.getPodcast(id);
    }

    public LiveData<List<PodcastDetails>> getAllPodcast(){
        return dao.getAllPodcast();
    }

    public int getCount(){
        return dao.getCount();
    }

}
