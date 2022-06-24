package bg.softuni.shoppingList.services;

import bg.softuni.shoppingList.models.User;
import bg.softuni.shoppingList.models.dto.LoginDTO;
import bg.softuni.shoppingList.models.dto.RegisterDTO;
import bg.softuni.shoppingList.repositories.UserRepository;
import bg.softuni.shoppingList.session.LoggedUser;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    private UserRepository userRepository;
    private LoggedUser userSession;

    public AuthService(UserRepository userRepository, LoggedUser userSession) {
        this.userRepository = userRepository;
        this.userSession = userSession;
    }

    public boolean register(RegisterDTO registerDTO){
        Optional<User> byUsername = this.userRepository.findByUsername(registerDTO.getUsername());
        if (byUsername.isPresent()){
            return false;
        }

        if(!registerDTO.getPassword().equals(registerDTO.getConfirmPassword())){
            return false;
        }

        User user = new User();
        user.setUsername(registerDTO.getUsername());
        user.setEmail(registerDTO.getEmail());
        user.setPassword(registerDTO.getPassword());

        this.userRepository.save(user);

        return true;
    }

    public boolean login(LoginDTO loginDTO){
        Optional<User> byUsernameAndPassword = this.userRepository.findByUsernameAndPassword(loginDTO.getUsername(), loginDTO.getPassword());

        if (byUsernameAndPassword.isEmpty()){
            return false;
        }

        this.userSession.login(byUsernameAndPassword.get());

        return true;
    }

    public boolean logout(){
        this.userSession.logout();
        return true;
    }

}
