package bg.softuni.pathfinder.service;

import bg.softuni.pathfinder.model.User;
import bg.softuni.pathfinder.model.dto.UserRegistrationDTO;
import bg.softuni.pathfinder.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    private UserRepository userRepository;

    public AuthService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public void register(UserRegistrationDTO registrationDTO){
        if (!registrationDTO.getPassword().equals(registrationDTO.getConfirmPassword())){
            throw new RuntimeException("password.match");
        }

        Optional<User> userOpt = this.userRepository.findByEmail(registrationDTO.getEmail());
        if (userOpt.isPresent()){
            throw new RuntimeException("email.used");
        }

        User user = new User(
                registrationDTO.getUsername(),
                registrationDTO.getPassword(),
                registrationDTO.getEmail(),
                registrationDTO.getFullname(),
                registrationDTO.getAge()
        );

        this.userRepository.save(user);
    }
}
