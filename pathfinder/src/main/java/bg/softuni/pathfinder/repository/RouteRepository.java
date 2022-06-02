package bg.softuni.pathfinder.repository;

import bg.softuni.pathfinder.model.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RouteRepository extends JpaRepository<Route, Long> {
    @Query("SELECT r FROM Route r ORDER BY size(r.comments) DESC")
    List<Route> findMostCommented();

}
