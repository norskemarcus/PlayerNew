package dat3.player.repository;

import dat3.player.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PlayerRepository extends JpaRepository<Player,Integer> {

  Player findPlayerByName(String name);

}

