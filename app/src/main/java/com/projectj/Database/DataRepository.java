package com.projectj.Database;


import android.content.Context;

import androidx.lifecycle.LiveData;

import java.util.List;

public class DataRepository {

    private final AppDao dao;
    private final LiveData<List<PodcastDetails>> membersList;

    public DataRepository(final Context context){

        AppDatabase database = AppDatabase.getInstance(context);
        dao = database.dao();
        membersList = dao.getAllPodcast();

    }

    public LiveData<List<PodcastDetails>> getAllPodcast(){
        return dao.getAllPodcast();
    }

    public int getCount(){
        return dao.getCount();
    }

}
