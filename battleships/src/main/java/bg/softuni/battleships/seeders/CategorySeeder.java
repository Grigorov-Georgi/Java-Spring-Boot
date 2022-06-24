package bg.softuni.battleships.seeders;

import bg.softuni.battleships.models.Category;
import bg.softuni.battleships.models.ShipType;
import bg.softuni.battleships.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class CategorySeeder implements CommandLineRunner {
    private CategoryRepository categoryRepository;

    @Autowired
    public CategorySeeder(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (categoryRepository.count() == 0){
            Arrays.stream(ShipType.values())
                    .map(Category::new)
                    .forEach(cat -> this.categoryRepository.save(cat));
        }
    }
}
