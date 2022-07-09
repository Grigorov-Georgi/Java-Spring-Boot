package com.example.spotifyplaylistapp.service;

import com.example.spotifyplaylistapp.model.entity.Song;
import com.example.spotifyplaylistapp.model.entity.User;
import com.example.spotifyplaylistapp.repository.SongRepository;
import com.example.spotifyplaylistapp.repository.UserRepository;
import com.example.spotifyplaylistapp.session.LoggedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service

public class PlaylistService {
    private UserRepository userRepository;
    private LoggedUser loggedUser;
    private SongRepository songRepository;

    @Autowired
    public PlaylistService(UserRepository userRepository, LoggedUser loggedUser, SongRepository songRepository) {
        this.userRepository = userRepository;
        this.loggedUser = loggedUser;
        this.songRepository = songRepository;
    }


    public void addToPlaylist(long id) {
        Optional<Song> selectedSong = this.songRepository.findById(id);
        this.loggedUser.addSong(selectedSong.get());
    }


    public void removeAll() {
        this.loggedUser.deletePlaylist();
    }
}
