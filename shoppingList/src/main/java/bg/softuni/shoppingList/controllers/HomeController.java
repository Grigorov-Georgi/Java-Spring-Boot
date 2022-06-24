package bg.softuni.shoppingList.controllers;

import bg.softuni.shoppingList.models.Product;
import bg.softuni.shoppingList.models.dto.ProductViewDTO;
import bg.softuni.shoppingList.services.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.math.BigDecimal;
import java.util.List;

@Controller
public class HomeController {
    private ProductService productService;

    public HomeController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/home")
    public String home(Model model){
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

    @ModelAttribute("totalPriceOfProducts")
    public BigDecimal initTotal(){
        return BigDecimal.valueOf(0);
    }

    @GetMapping("/home/buy")
    public String buy(Model model){
        Object currentTotal = model.getAttribute("totalPriceOfProducts");
        model.addAttribute("totalPriceOfProducts", currentTotal );
    }
}
