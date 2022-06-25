package bg.softuni.shoppingList.controllers;

import bg.softuni.shoppingList.models.dto.AddProductDTO;
import bg.softuni.shoppingList.services.AuthService;
import bg.softuni.shoppingList.services.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class ProductController {
    private ProductService productService;
    private AuthService authService;

    public ProductController(ProductService productService, AuthService authService) {
        this.productService = productService;
        this.authService = authService;
    }

    @ModelAttribute("addProductDTO")
    public AddProductDTO initAddProductDTO(){
        return new AddProductDTO();
    }

    @GetMapping("/product/add")
    public String product(){
        if (!authService.isLoggedIn()){
            return "redirect:/";
        }
        return "product-add";
    }

    @PostMapping("/product/add")
    public String product(@Valid AddProductDTO addProductDTO,
                          BindingResult bindingResult,
                          RedirectAttributes redirectAttributes){

        if (!authService.isLoggedIn()){
            return "redirect:/";
        }

        if (bindingResult.hasErrors() || !this.productService.create(addProductDTO)){
            redirectAttributes.addFlashAttribute("addProductDTO", addProductDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addProductDTO", bindingResult);

            return "redirect:/product/add";
        }

        return "redirect:/home";
    }
}
