package bg.softuni.pathfinder.service;

import bg.softuni.pathfinder.model.Route;
import bg.softuni.pathfinder.repository.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RouteService {
    private RouteRepository routeRepository;

    @Autowired
    public RouteService(RouteRepository routeRepository){
        this.routeRepository = routeRepository;
    }

    public Route getMostCommented() {
        return routeRepository.findMostCommented().get(0);
    }
}
