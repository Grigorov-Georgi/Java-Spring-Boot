package bg.softuni.mobilelele.service;

import bg.softuni.mobilelele.model.dto.UserLoginDTO;
import bg.softuni.mobilelele.model.entity.UserEntity;
import bg.softuni.mobilelele.repository.UserRepository;
import bg.softuni.mobilelele.user.CurrentUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    private final CurrentUser currentUser;
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository, CurrentUser currentUser) {
        this.userRepository = userRepository;
        this.currentUser = currentUser;
    }


    public boolean login(UserLoginDTO loginDTO){
        Optional<UserEntity> userOpt =
        userRepository.findByEmail(loginDTO.getUsername());

        if (userOpt.isEmpty()){
            LOGGER.info("User not found");
            return  false;
        }

        boolean success = userOpt.get().getPassword().equals(loginDTO.getPassword());

        if (success){
            login(userOpt.get());
        } else {
            logout();
        }

        return success;
    }


    private void login(UserEntity userEntity){
        currentUser.
                setLoggedIn(true);
        currentUser.
                setName(userEntity.getFirstName() + " " + userEntity.getLastName());
    }

    public void logout(){
        currentUser.clear();
    }
}
