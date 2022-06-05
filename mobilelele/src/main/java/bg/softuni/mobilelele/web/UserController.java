package bg.softuni.mobilelele.web;

import bg.softuni.mobilelele.model.dto.UserLoginDTO;
import bg.softuni.mobilelele.model.dto.UserRegisterDTO;
import bg.softuni.mobilelele.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login(){
        return "auth-login";
    }

    @PostMapping("/login")
    public String login(UserLoginDTO userLoginDTO){
        System.out.println("User is logged: " + userService.login(userLoginDTO));

        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(){
        userService.logout();
        return "redirect:/";
    }

    @GetMapping("/register")
    public String register(){
        return  "auth-register";
    }

    @PostMapping("/register")
    public String register(UserRegisterDTO userRegisterDTO){

        userService.registerAndLogin(userRegisterDTO);

        return "redirect:/";
    }
}
