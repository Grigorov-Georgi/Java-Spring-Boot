package bg.softuni.battleships.controllers;

import bg.softuni.battleships.models.dto.ShipDTO;
import bg.softuni.battleships.models.dto.StartBattleDTO;
import bg.softuni.battleships.services.AuthService;
import bg.softuni.battleships.services.ShipService;
import bg.softuni.battleships.session.LoggedUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Controller
public class HomeController {
    private ShipService shipService;
    private LoggedUser loggedUser;
    private AuthService authService;

    public HomeController(ShipService shipService, LoggedUser loggedUser, AuthService authService) {
        this.shipService = shipService;
        this.loggedUser = loggedUser;
        this.authService = authService;
    }

    @ModelAttribute("startBattleDTO")
    public StartBattleDTO initStartBattleForm(){
        return new StartBattleDTO();
    }

    @GetMapping("/")
    public String loggedOutIndex(){
        if(this.authService.isLoggedIn()){
            return "redirect:/home";
        }
        return "index";
    }

    @GetMapping("/home")
    public String loggedInIndex(Model model){
        if(!this.authService.isLoggedIn()){
            return "redirect:/";
        }

        long loggedUserId = this.loggedUser.getId();

        List<ShipDTO> ownShips = this.shipService.getShipsOwnedBy(loggedUserId);
        List<ShipDTO> enemyShips = this.shipService.getShipsNotOwnedBy(loggedUserId);
        List<ShipDTO> sortedShips = this.shipService.getAllSorted();

        model.addAttribute("ownShips", ownShips);
        model.addAttribute("enemyShips", enemyShips);
        model.addAttribute("sortedShips", sortedShips);
        return "home";
    }
}
