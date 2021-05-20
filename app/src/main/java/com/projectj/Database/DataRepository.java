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

    public LiveData<List<PodcastDetails>> getAllPodcast(){
        return dao.getAllPodcast();
    }

    public int getCount(){
        return dao.getCount();
    }

}
