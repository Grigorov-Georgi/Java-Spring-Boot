package com.example.spotifyplaylistapp.model.dto;

import com.example.spotifyplaylistapp.model.entity.Song;

public class PlaylistSongDTO {
    private String performer;
    private String title;
    private String duration;
    private int seconds;

    public PlaylistSongDTO(Song song){
        this.performer = song.getPerformer();
        this.title = song.getTitle();
        this.duration = calculateDuration(song.getDuration());
        this.seconds = song.getDuration();
    }

    public String getPerformer() {
        return performer;
    }

    public void setPerformer(String performer) {
        this.performer = performer;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    private String calculateDuration(int duration) {
        StringBuilder sb = new StringBuilder();
        sb.append(duration / 60).append(":");
        if(duration % 60 < 10){
            sb.append(0);
        }
        sb.append(duration % 60);

        return sb.toString();
    }
}
