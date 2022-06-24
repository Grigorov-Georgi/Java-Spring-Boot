package bg.softuni.shoppingList.services;

import bg.softuni.shoppingList.models.Category;
import bg.softuni.shoppingList.models.CategoryEnum;
import bg.softuni.shoppingList.models.Product;
import bg.softuni.shoppingList.models.dto.AddProductDTO;
import bg.softuni.shoppingList.models.dto.ProductViewDTO;
import bg.softuni.shoppingList.repositories.CategoryRepository;
import bg.softuni.shoppingList.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    public boolean create(AddProductDTO addProductDTO){
        Optional<Product> byName = this.productRepository.findByName(addProductDTO.getName());
        if (byName.isPresent()){
            return false;
        }

        CategoryEnum categoryEnum = switch (addProductDTO.getCategory()){
            case "FOOD" -> CategoryEnum.Food;
            case "DRINK" -> CategoryEnum.Drink;
            case "HOUSEHOLD" -> CategoryEnum.Household;
            case "OTHER" -> CategoryEnum.Other;
            default -> CategoryEnum.Other;
        };

        Category categoryFromDB = this.categoryRepository.findByName(categoryEnum);

        Product product = new Product();
        product.setName(addProductDTO.getName());
        product.setDescription(addProductDTO.getDescription());
        product.setNeededBefore(addProductDTO.getNeededBefore());
        product.setPrice(addProductDTO.getPrice());
        product.setCategory(categoryFromDB);

        this.productRepository.save(product);

        return true;
    }

    public List<ProductViewDTO> getAllDrinks() {
        Category drink = this.categoryRepository.findByName(CategoryEnum.Drink);
        List<ProductViewDTO> collect = this.productRepository.findByCategoryId(drink.getId())
                .stream()
                .map(p -> new ProductViewDTO(p))
                .collect(Collectors.toList());
        return collect;
    }

    public List<ProductViewDTO> getAllFoods() {
        Category food = this.categoryRepository.findByName(CategoryEnum.Food);
        return this.productRepository.findByCategoryId(food.getId())
                .stream()
                .map(p -> new ProductViewDTO(p))
                .collect(Collectors.toList());
    }
    public List<ProductViewDTO> getAllHousehold() {
        Category household = this.categoryRepository.findByName(CategoryEnum.valueOf("Household"));
        return this.productRepository.findByCategoryId(household.getId())
                .stream()
                .map(p -> new ProductViewDTO(p))
                .collect(Collectors.toList());
    }
    public List<ProductViewDTO> getAllOther() {
        Category other = this.categoryRepository.findByName(CategoryEnum.valueOf("Other"));
        return this.productRepository.findByCategoryId(other.getId())
                .stream()
                .map(p -> new ProductViewDTO(p))
                .collect(Collectors.toList());
    }
}
