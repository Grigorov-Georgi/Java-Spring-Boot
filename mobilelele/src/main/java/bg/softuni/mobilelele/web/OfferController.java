package bg.softuni.mobilelele.web;

import bg.softuni.mobilelele.model.dto.AddOfferDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class OfferController {

    @GetMapping("/offers/all")
    public String allOffers(){
        return "offers";
    }

    @GetMapping("/offers/add")
    public String addOffer(Model model){
        if (!model.containsAttribute("addOfferModel")){
            model.addAttribute("addOfferModel", new AddOfferDTO());
        }
        return "offer-add";
    }

    @PostMapping("/offers/add")
    public String addOffer(AddOfferDTO addOfferDTO){

        return "offers";
    }


}
