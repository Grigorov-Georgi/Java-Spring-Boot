package bg.softuni.shoppingList.repositories;

import bg.softuni.shoppingList.models.Category;
import bg.softuni.shoppingList.models.CategoryEnum;
import bg.softuni.shoppingList.models.Product;
import bg.softuni.shoppingList.models.dto.ProductViewDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByName(String name);

    List<Product> findByCategory(Category category);

    List<Product> findByCategoryId(long i);
}
