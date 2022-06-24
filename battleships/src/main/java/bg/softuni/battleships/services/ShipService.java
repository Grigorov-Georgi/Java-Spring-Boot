package bg.softuni.battleships.services;

import bg.softuni.battleships.models.Category;
import bg.softuni.battleships.models.Ship;
import bg.softuni.battleships.models.ShipType;
import bg.softuni.battleships.models.User;
import bg.softuni.battleships.models.dto.CreateShipDTO;
import bg.softuni.battleships.models.dto.ShipDTO;
import bg.softuni.battleships.repositories.CategoryRepository;
import bg.softuni.battleships.repositories.ShipRepository;
import bg.softuni.battleships.repositories.UserRepository;
import bg.softuni.battleships.session.LoggedUser;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ShipService {

    private ShipRepository shipRepository;
    private CategoryRepository categoryRepository;
    private UserRepository userRepository;
    private LoggedUser loggedUser;

    public ShipService(ShipRepository shipRepository,
                       CategoryRepository categoryRepository,
                       UserRepository userRepository,
                       LoggedUser loggedUser) {
        this.shipRepository = shipRepository;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
        this.loggedUser = loggedUser;
    }

    public boolean create(CreateShipDTO createShipDTO){
        Optional<Ship> byName = this.shipRepository.findByName(createShipDTO.getName());

        if (byName.isPresent()){
            return false;
        }

        ShipType type = switch (createShipDTO.getCategory()){
            case 0 -> ShipType.BATTLE;
            case 1 -> ShipType.CARGO;
            case 2 -> ShipType.PATROL;
            default -> ShipType.BATTLE;
        };

        Category category = this.categoryRepository.findByName(type);
        System.out.println();
        Optional<User> owner = this.userRepository.findById(this.loggedUser.getId());

        Ship ship = new Ship();

        ship.setName(createShipDTO.getName());
        ship.setPower(createShipDTO.getPower());
        ship.setHealth(createShipDTO.getHealth());
        ship.setCategory(category);
        ship.setCreated(createShipDTO.getCreated());
        ship.setOwner(owner.get());

        this.shipRepository.save(ship);
        return true;
    }

    public List<ShipDTO> getShipsOwnedBy(long ownerId) {
        return this.shipRepository.findByUserId(ownerId)
                .stream()
                .map(ship -> new ShipDTO(ship))
                .collect(Collectors.toList());
    }

    public List<ShipDTO> getShipsNotOwnedBy(long ownerId) {
        return this.shipRepository.findByUserIdNot(ownerId)
                .stream()
                .map(ship -> new ShipDTO(ship))
                .collect(Collectors.toList());
    }

    public List<ShipDTO> getAllSorted() {
        return this.shipRepository.findByOrderByNameAscHealthAscPowerAsc()
                .stream()
                .map(ship -> new ShipDTO(ship))
                .collect(Collectors.toList());
    }
}
