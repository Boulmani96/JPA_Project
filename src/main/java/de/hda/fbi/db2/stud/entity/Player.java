package de.hda.fbi.db2.stud.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Player", schema = "public")
@NamedQueries(@NamedQuery(name="player.findByName",query = "select p from Player p where p.name =:name"))
public class Player {
  @Id
  private String name;
  @OneToMany (mappedBy = "player"//, cascade = CascadeType.PERSIST
      )
  private List<Game> games;

  public Player() {

  }

  public Player(String name) {
    this.name = name;
    games = new ArrayList<>();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Player player = (Player) o;
    return name.equals(player.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name);
  }

  public List<Game> getGames() {
    return games;
  }

  public void setGames(List<Game> games) {
    this.games = games;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
