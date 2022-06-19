package bg.softuni.mobilelele.service;

import bg.softuni.mobilelele.model.dto.UserLoginDTO;
import bg.softuni.mobilelele.model.dto.UserRegisterDTO;
import bg.softuni.mobilelele.model.entity.UserEntity;
import bg.softuni.mobilelele.model.mapper.UserMapper;
import bg.softuni.mobilelele.repository.UserRepository;
import bg.softuni.mobilelele.user.CurrentUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    private final CurrentUser currentUser;
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository, CurrentUser currentUser, PasswordEncoder passwordEncoder, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.currentUser = currentUser;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
    }


    public boolean login(UserLoginDTO loginDTO){
        Optional<UserEntity> userOpt =
        userRepository.findByEmail(loginDTO.getUsername());

        if (userOpt.isEmpty()){
            LOGGER.info("User not found");
            return  false;
        }

        var rawPassword = loginDTO.getPassword();
        var encodedPassword = userOpt.get().getPassword();

        boolean success = passwordEncoder.matches(rawPassword, encodedPassword);

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

    public void registerAndLogin(UserRegisterDTO userRegisterDTO){

        UserEntity newUser = userMapper.userDtoToUserEntity(userRegisterDTO);
        newUser.setPassword(passwordEncoder.encode(userRegisterDTO.getPassword()));

        userRepository.save(newUser);

        login(newUser);
    }

    public void logout(){
        currentUser.clear();
    }
}
