package bg.softuni.shoppingList.seeders;

import bg.softuni.shoppingList.models.Category;
import bg.softuni.shoppingList.models.CategoryEnum;
import bg.softuni.shoppingList.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class CategorySeeder implements CommandLineRunner {
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategorySeeder(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    @Override
    public void run(String... args) throws Exception {
        if(this.categoryRepository.count() == 0){
            Arrays.stream(CategoryEnum.values())
                    .map(Category::new)
                    .forEach(this.categoryRepository::save);
        }
    }
}
