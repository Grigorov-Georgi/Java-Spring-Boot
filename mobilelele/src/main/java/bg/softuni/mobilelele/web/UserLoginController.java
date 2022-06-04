package bg.softuni.mobilelele.web;

import bg.softuni.mobilelele.model.dto.UserLoginDTO;
import bg.softuni.mobilelele.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserLoginController {

    private final UserService userService;

    @Autowired
    public UserLoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users/login")
    public String login(){
        return "auth-login";
    }

    @PostMapping("/user/login")
    public String login(UserLoginDTO userLoginDTO){
        System.out.println("User is logged: " + userService.login(userLoginDTO));

        return "redirect:/";
    }

    @GetMapping("/users/logout")
    public String logout(){
        userService.logout();
        return "redirect:/";
    }
}
