package bg.softuni.shoppingList.controllers;


import bg.softuni.shoppingList.models.dto.ProductViewDTO;
import bg.softuni.shoppingList.services.AuthService;
import bg.softuni.shoppingList.services.BuyService;
import bg.softuni.shoppingList.services.ProductService;
import bg.softuni.shoppingList.session.LoggedUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.List;

@Controller
public class HomeController {
    private ProductService productService;
    private BuyService buyService;
    private LoggedUser loggedUser;
    private AuthService authService;


    public HomeController(ProductService productService, BuyService buyService, LoggedUser loggedUser, AuthService authService) {
        this.productService = productService;
        this.buyService = buyService;
        this.loggedUser = loggedUser;
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

        List<ProductViewDTO> drinksList = this.productService.getAllDrinks();
        List<ProductViewDTO> foodsList = this.productService.getAllFoods();
        List<ProductViewDTO> householdList = this.productService.getAllHousehold();
        List<ProductViewDTO> otherList = this.productService.getAllOther();

        model.addAttribute("drinksList", drinksList);
        model.addAttribute("foodsList", foodsList);
        model.addAttribute("householdList", householdList);
        model.addAttribute("otherList", otherList);


        return "home";
    }

//    @ModelAttribute("buyDTO")
//    public BuyDTO initTotal(){
//        return new BuyDTO();
//    }

    @GetMapping("/home/buy")
    public String buy(@RequestParam long id, Model model){
        if (!authService.isLoggedIn()){
            return "redirect:/";
        }
        model.addAttribute("total", this.buyService.buy(id));
        return "redirect:/home";
    }

    @GetMapping("/home/buy/all")
    public String buyAll(){
        if (!authService.isLoggedIn()){
            return "redirect:/";
        }
        this.buyService.buyAll();
        this.loggedUser.setTotal(BigDecimal.valueOf(0));
        return "redirect:/home";
    }

}
