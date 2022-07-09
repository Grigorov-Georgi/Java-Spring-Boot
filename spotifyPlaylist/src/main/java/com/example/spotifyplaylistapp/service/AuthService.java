package com.example.spotifyplaylistapp.service;

import com.example.spotifyplaylistapp.model.dto.LoginDTO;
import com.example.spotifyplaylistapp.model.dto.RegisterDTO;
import com.example.spotifyplaylistapp.model.entity.User;
import com.example.spotifyplaylistapp.repository.UserRepository;
import com.example.spotifyplaylistapp.session.LoggedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    private UserRepository userRepository;
    private LoggedUser userSession;

    @Autowired
    public AuthService(UserRepository userRepository, LoggedUser userSession) {
        this.userRepository = userRepository;
        this.userSession = userSession;
    }

    public boolean register(RegisterDTO registerDTO) {
        Optional<User> byUsername = this.userRepository.findByUsername(registerDTO.getUsername());
        Optional<User> byEmail = this.userRepository.findByEmail(registerDTO.getEmail());

        if (byUsername.isPresent() || byEmail.isPresent()) {
            return false;
        }

        if (!registerDTO.getPassword().equals(registerDTO.getConfirmPassword())){
            return false;
        }

        User user = new User();
        user.setUsername(registerDTO.getUsername());
        user.setEmail(registerDTO.getEmail());
        user.setPassword(registerDTO.getPassword());

        this.userRepository.save(user);

        return true;
    }

    public boolean login(LoginDTO loginDTO) {
        Optional<User> byUsernameAndPassword = this.userRepository.findByUsernameAndPassword(loginDTO.getUsername(), loginDTO.getPassword());

        if (byUsernameAndPassword.isEmpty()) {
            return false;
        }

        this.userSession.login(byUsernameAndPassword.get());

        return true;
    }

    public void logout(){
        this.userSession.logout();
    }

    public boolean isLoggedIn(){
        return this.userSession.getId() > 0;
    }
}
