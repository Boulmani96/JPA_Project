package de.hda.fbi.db2.stud.entity;


import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name = "Game", schema = "public")
public class Game {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "public.id_game")
  @SequenceGenerator(name = "public.id_game", sequenceName = "public.id_game", initialValue = 1, allocationSize = 1000)
  private int id;

  @ManyToOne (cascade = {CascadeType.PERSIST})
  private Player player;

  @ElementCollection
  @Column (name="antwort")
  private Map<Question,Integer> myquestionmap = new HashMap<>();//= new HashMap<Question,Integer>();

  private LocalDateTime startTime;

  private LocalDateTime endTime;

  public Game() {

  }

  public Game(Player player, Map<Question, Integer> myquestionmap) {
    this.player = player;
    this.myquestionmap = myquestionmap;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Game game = (Game) o;
    return id == game.id;
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

  public LocalDateTime getStart() {
    return startTime;
  }

  public void setStart(LocalDateTime start) {
    this.startTime = start;
  }

  public LocalDateTime getEnd() {
    return endTime;
  }

  public void setEnd(LocalDateTime end) {
    this.endTime = end;
  }

  public int getId() {
    return id;
  }

  public Player getPlayer() {
    return player;
  }

  public Map<Question, Integer> getMyQuetionMap() {
    return myquestionmap;
  }
  
}
