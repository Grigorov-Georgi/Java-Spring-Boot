package com.example.spotifyplaylistapp.controller;

import com.example.spotifyplaylistapp.model.dto.SongViewDTO;
import com.example.spotifyplaylistapp.service.AuthService;
import com.example.spotifyplaylistapp.service.PlaylistService;
import com.example.spotifyplaylistapp.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class HomeController {

    private SongService songService;
    private PlaylistService playlistService;
    private AuthService authService;

    @Autowired
    public HomeController(SongService songService, PlaylistService playlistService, AuthService authService) {
        this.songService = songService;
        this.playlistService = playlistService;
        this.authService = authService;
    }

    @GetMapping("/")
    public String index(){
        if (authService.isLoggedIn()){
            return "redirect:/home";
        }
        return "index";
    }

    @GetMapping("/home")
    public String home(Model model){
        if (!authService.isLoggedIn()){
            return "redirect:/";
        }

        List<SongViewDTO> popSongs = this.songService.getAllPopSongs();
        List<SongViewDTO> jazzSongs = this.songService.getAllJazzSongs();
        List<SongViewDTO> rockSongs = this.songService.getAllRockSongs();

        model.addAttribute("popSongs", popSongs);
        model.addAttribute("jazzSongs", jazzSongs);
        model.addAttribute("rockSongs", rockSongs);

        return "home";
    }

//    @ModelAttribute("userPlaylist")
//    public List<Song> initPlaylist(){
//        return new ArrayList<>();
//    }

//    @GetMapping("/home/playlist")
//    public String playlist(@RequestParam long id, Model model){
//        User user = this.playlistService.addToPlaylist(id);
//        model.addAttribute("userPlaylist", user.getPlaylist());
//        return "redirect:/home";
//    }

    @GetMapping("/home/playlist")
    public String playlist(@RequestParam long id){
        if (!authService.isLoggedIn()){
            return "redirect:/";
        }
        this.playlistService.addToPlaylist(id);
        return "redirect:/home";
    }

    @GetMapping("/home/remove/all")
    public String removeAll(){
        if (!authService.isLoggedIn()){
            return "redirect:/";
        }
        this.playlistService.removeAll();
        return "redirect:/home";
    }
}
