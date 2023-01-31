package dat3.player.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
  public class Player {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private String name;


    public Player() {
    }

    public Player(String name) {
      this.name = name;
    }


    public String getName() {
      return name;
    }

  public int getId() {
    return id;
  }


}



