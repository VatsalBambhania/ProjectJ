package com.projectj.Database;

public class PodcastDetails {

    @Primary
    int id;
    String name;
    String duration;
    String podcastBy;

    public PodcastDetails(String duration, int id, String name, String podcastBy) {
        this.id = id;
        this.name = name;
        this.duration = duration;
        this.podcastBy = podcastBy;
    }

}
