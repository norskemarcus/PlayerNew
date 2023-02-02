package dat3.player.api;

import dat3.player.entity.Player;
import dat3.player.repository.PlayerRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// giver rest API

// husk at dette er URL når man skal teste localhost:
// http://localhost:9046/api/players

@RestController
@RequestMapping("/api/players")
public class PlayerController {


  PlayerRepository playerRepository;


  public PlayerController(PlayerRepository playerRepository) {
    this.playerRepository = playerRepository;
  }


  @GetMapping
  Iterable<Player> getPlayers() {
    return playerRepository.findAll();
  }
}
