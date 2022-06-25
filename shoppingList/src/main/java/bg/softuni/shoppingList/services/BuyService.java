package bg.softuni.shoppingList.services;

import bg.softuni.shoppingList.models.Product;
import bg.softuni.shoppingList.models.dto.BuyDTO;
import bg.softuni.shoppingList.repositories.ProductRepository;
import bg.softuni.shoppingList.session.LoggedUser;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class BuyService {
    private ProductRepository productRepository;
    private LoggedUser loggedUser;

    public BuyService(ProductRepository productRepository, LoggedUser loggedUser) {
        this.productRepository = productRepository;
        this.loggedUser = loggedUser;
    }

    public BigDecimal buy(long id){
        Optional<Product> byId = this.productRepository.findById(id);
        this.loggedUser.setTotal(this.loggedUser.getTotal().add(byId.get().getPrice()));

        this.productRepository.delete(byId.get());

        return this.loggedUser.getTotal();
    }

    public void buyAll(){
        this.productRepository.deleteAll();
    }

}
