package com.example.spotifyplaylistapp.session;

import com.example.spotifyplaylistapp.model.dto.PlaylistSongDTO;
import com.example.spotifyplaylistapp.model.entity.Song;
import com.example.spotifyplaylistapp.model.entity.User;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@SessionScope
public class LoggedUser {
    private long id;
    private List<Song> playlist;
    private List<PlaylistSongDTO> reworkedPlaylist;

    public void login(User user) {
        this.id = user.getId();
        this.playlist = user.getPlaylist();
        this.reworkedPlaylist = rotate(user.getPlaylist());
    }

    public void logout() {
        this.id = 0;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Song> getPlaylist() {
        return playlist;
    }

    public void setPlaylist(List<Song> playlist) {
        this.playlist = playlist;
    }

    public void addSong(Song song) {
        this.playlist.add(song);
        this.reworkedPlaylist = rotate(playlist);
    }

    public List<PlaylistSongDTO> rotate(List<Song> songs) {
        return songs
                .stream()
                .map(s -> new PlaylistSongDTO(s))
                .collect(Collectors.toList());
    }

    public List<PlaylistSongDTO> getReworkedPlaylist() {
        return reworkedPlaylist;
    }

    public void setReworkedPlaylist(List<PlaylistSongDTO> reworkedPlaylist) {
        this.reworkedPlaylist = reworkedPlaylist;
    }

    public void deletePlaylist() {
        this.playlist = new ArrayList<>();
        this.reworkedPlaylist = rotate(playlist);
    }

    public String getTotalDuration() {
        int total = 0;
        for (Song song : playlist) {
            total += song.getDuration();
        }

        StringBuilder sb = new StringBuilder();
        sb.append(total / 60).append(":");
        if (total % 60 < 10) {
            sb.append(0);
        }
        sb.append(total % 60);

        return sb.toString();
    }
}
