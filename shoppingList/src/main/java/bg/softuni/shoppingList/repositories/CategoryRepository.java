package bg.softuni.shoppingList.repositories;

import bg.softuni.shoppingList.models.Category;
import bg.softuni.shoppingList.models.CategoryEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByName(CategoryEnum categoryEnum);
}
