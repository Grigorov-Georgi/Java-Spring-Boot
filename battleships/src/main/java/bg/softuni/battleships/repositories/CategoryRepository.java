package bg.softuni.battleships.repositories;

import bg.softuni.battleships.models.Category;
import bg.softuni.battleships.models.ShipType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByName(ShipType type);
}
