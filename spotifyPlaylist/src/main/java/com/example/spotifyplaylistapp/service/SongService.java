package com.example.spotifyplaylistapp.service;

import com.example.spotifyplaylistapp.model.dto.AddSongDTO;
import com.example.spotifyplaylistapp.model.dto.SongViewDTO;
import com.example.spotifyplaylistapp.model.entity.Song;
import com.example.spotifyplaylistapp.model.entity.Style;
import com.example.spotifyplaylistapp.model.entity.StyleEnum;
import com.example.spotifyplaylistapp.repository.SongRepository;
import com.example.spotifyplaylistapp.repository.StyleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SongService {

    private SongRepository songRepository;
    private StyleRepository styleRepository;

    @Autowired
    public SongService(SongRepository songRepository, StyleRepository styleRepository) {
        this.songRepository = songRepository;
        this.styleRepository = styleRepository;
    }

    public boolean create(AddSongDTO addSongDTO){
        Optional<Song> byPerformer = this.songRepository.findByPerformer(addSongDTO.getPerformer());

        if (byPerformer.isPresent()){
            return false;
        }

        StyleEnum styleEnum = switch (addSongDTO.getStyle()){
            case "POP" -> StyleEnum.POP;
            case "ROCK" -> StyleEnum.ROCK;
            case "JAZZ" -> StyleEnum.JAZZ;
            default -> StyleEnum.JAZZ;
        };

        Style style = this.styleRepository.findByName(styleEnum);

        Song song = new Song();
        song.setPerformer(addSongDTO.getPerformer());
        song.setTitle(addSongDTO.getTitle());
        song.setReleaseDate(addSongDTO.getReleaseDate());
        song.setDuration(addSongDTO.getDuration());
        song.setStyle(style);

        this.songRepository.save(song);
        return true;
    }

    public List<SongViewDTO> getAllPopSongs() {
        return this.songRepository.findByStyleName(StyleEnum.valueOf("POP"))
                .stream()
                .map(SongViewDTO::new)
                .collect(Collectors.toList());
    }

    public List<SongViewDTO> getAllJazzSongs() {
        return this.songRepository.findByStyleName(StyleEnum.valueOf("JAZZ"))
                .stream()
                .map(SongViewDTO::new)
                .collect(Collectors.toList());
    }

    public List<SongViewDTO> getAllRockSongs() {
        return this.songRepository.findByStyleName(StyleEnum.valueOf("ROCK"))
                .stream()
                .map(SongViewDTO::new)
                .collect(Collectors.toList());
    }

    public List<SongViewDTO> removeSong(List<SongViewDTO> list, Song song){
        int counter = 0;
        for (SongViewDTO songViewDTO : list) {
            if (songViewDTO.getId() != song.getId()) {
                counter++;
            } else {
                break;
            }
        }
        list.remove(counter);

        return list;
    }
}
