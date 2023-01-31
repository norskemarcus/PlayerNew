package dat3.player.repository;

import dat3.player.entity.Player;
import org.springframework.data.repository.CrudRepository;

public interface PlayerRepository extends CrudRepository<Player,Integer> {

  // CRUD repo

  // Why does Lars extended JPA Repo?
}

