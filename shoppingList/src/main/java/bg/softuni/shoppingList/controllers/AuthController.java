package bg.softuni.shoppingList.controllers;

import bg.softuni.shoppingList.models.dto.LoginDTO;
import bg.softuni.shoppingList.models.dto.RegisterDTO;
import bg.softuni.shoppingList.services.AuthService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class AuthController {
    private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @ModelAttribute("loginDTO")
    public LoginDTO initLoginDTO(){
        return new LoginDTO();
    }

    @GetMapping("/login")
    public String login(){
        if (authService.isLoggedIn()){
            return "redirect:/home";
        }
        return "/login";
    }

    @PostMapping("/login")
    public String login(@Valid LoginDTO loginDTO,
                        BindingResult bindingResult,
                        RedirectAttributes redirectAttributes){

        if (authService.isLoggedIn()){
            return "redirect:/home";
        }

        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("loginDTO", loginDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.loginDTO", bindingResult);

            return "redirect:/login";
        }

        if(!this.authService.login(loginDTO)){
            redirectAttributes.addFlashAttribute("loginDTO", loginDTO);
            redirectAttributes.addFlashAttribute("badCredentials", true);
            return "redirect:/login";
        }

        return "redirect:/home";
    }

    @ModelAttribute("registerDTO")
    public RegisterDTO initRegisterDTO(){
        return new RegisterDTO();
    }

    @GetMapping("/register")
    public String register(){
        if (authService.isLoggedIn()){
            return "redirect:/home";
        }
        return "/register";
    }

    @PostMapping("/register")
    public String register(@Valid RegisterDTO registerDTO,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes){

        if (authService.isLoggedIn()){
            return "redirect:/home";
        }

        if (bindingResult.hasErrors() || !this.authService.register(registerDTO)){
            redirectAttributes.addFlashAttribute("registerDTO", registerDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.registerDTO", bindingResult);

            return "redirect:/register";
        }

        return "redirect:/login";
    }

    @GetMapping("/logout")
    public String logout(){
        this.authService.logout();
        return "redirect:/";
    }
}
