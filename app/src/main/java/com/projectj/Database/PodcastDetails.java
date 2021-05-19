package com.projectj.Database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "PodcastDetails")
public class PodcastDetails {

    @PrimaryKey(autoGenerate = true)
    public int id;

    public String name;
    public String duration;
    public  String podcastBy;

    public PodcastDetails(String name, String duration, String podcastBy) {
        this.name = name;
        this.duration = duration;
        this.podcastBy = podcastBy;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getPodcastBy() {
        return podcastBy;
    }

    public void setPodcastBy(String podcastBy) {
        this.podcastBy = podcastBy;
    }
}
