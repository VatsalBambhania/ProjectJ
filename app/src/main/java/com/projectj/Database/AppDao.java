package com.projectj.Database;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;


import java.util.List;

@Dao
public interface AppDao {

    @Query("SELECT * FROM PodcastDetails")
    LiveData<List<PodcastDetails>> getAllPodcast();

    @Query("SELECT count(*) FROM PodcastDetails")
    int getCount();

    @Insert
    void AddPodcast(PodcastDetails details);

    @Delete
    void RemovePerson(PodcastDetails details);

    @Update
    void UpdatePerson(PodcastDetails details);

}
