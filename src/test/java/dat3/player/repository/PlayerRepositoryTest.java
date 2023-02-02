package dat3.player.repository;

import dat3.player.entity.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


// bruger H2 databasen
  @DataJpaTest
  class PlayerRepositoryTest {

    @Autowired
    private PlayerRepository playerRepository;

    boolean dataIsInitalized = false;

    @BeforeEach // kører før hver test
    public void setupPlayers() {
      if (!dataIsInitalized) {
        List<String> players = new ArrayList<>(Arrays.asList("aa", "bb", "cc"));
        // Lægger den ene listen over i den andre: lambda ->
        List<Player> playerEnities = players.stream().map(p -> new Player(p)).toList();
        playerRepository.saveAll(playerEnities);
        dataIsInitalized = true;
      }
    }

    @Test
    public void testGetAll() {
      //Will this test pass, if it runs AFTER the next text
      List<Player> players = playerRepository.findAll();
      assertEquals(3, players.size());
    }

    // Samme hvilken rækkefølge, fordi det er en rollback
    @Test
    public void testAddPlayer() {
      Player player = playerRepository.save(new Player("dd"));
      //Q1: What's the difference between the player we add, and the player returned by the save method?
      // Den første har ikke fået en id, mens denne får en id
      assertTrue(player.getId() > 0);
      long count = playerRepository.count();
      assertEquals(4, count);
    }

    @Test
    public void testFindPlayerByName() {
      // Q1: Write this test if you can. If not implement the missing parts
      // Q2: Can You find player "dd" if this test runs after the previous test
      Player p = playerRepository.findPlayerByName("bb");
      assertEquals("bb", p.getName());

    }
  }




