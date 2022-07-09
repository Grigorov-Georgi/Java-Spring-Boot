package com.example.spotifyplaylistapp.controller;

import com.example.spotifyplaylistapp.model.dto.AddSongDTO;
import com.example.spotifyplaylistapp.service.AuthService;
import com.example.spotifyplaylistapp.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class SongController {
    private SongService songService;
    private AuthService authService;

    @Autowired
    public SongController(SongService songService, AuthService authService) {
        this.songService = songService;
        this.authService = authService;
    }

    @ModelAttribute("addSongDTO")
    public AddSongDTO initAddSongDTO(){
        return new AddSongDTO();
    }

    @GetMapping("/songs/add")
    public String addSong(){
        if (!authService.isLoggedIn()){
            return "redirect:/";
        }
        return "song-add";
    }

    @PostMapping("/songs/add")
    public String addSong(@Valid AddSongDTO addSongDTO,
                        BindingResult bindingResult,
                        RedirectAttributes redirectAttributes){

        if (!authService.isLoggedIn()){
            return "redirect:/";
        }

        if(bindingResult.hasErrors() || !this.songService.create(addSongDTO)){
            redirectAttributes.addFlashAttribute("addSongDTO", addSongDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addSongDTO", bindingResult);

            return "redirect:/songs/add";
        }



        return "redirect:/home";
    }


}
