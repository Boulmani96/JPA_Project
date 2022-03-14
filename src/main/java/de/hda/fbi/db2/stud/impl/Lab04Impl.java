package de.hda.fbi.db2.stud.impl;

import de.hda.fbi.db2.api.Lab04MassData;
import de.hda.fbi.db2.stud.entity.Category;
import de.hda.fbi.db2.stud.entity.Game;
import de.hda.fbi.db2.stud.entity.Player;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.persistence.EntityManager;

public class Lab04Impl extends Lab04MassData {

  @Override
  public void createMassData() {
    LocalTime start = LocalTime.now();
    Random rand = new Random();
    EntityManager em = this.lab02EntityManager.getEntityManager();
    em.getTransaction().begin();
   // em.createQuery("select q from Question q join fetch q.answerList").getResultList();
    List<Category> resultL = lab01Data.getCategories();
    for (int i = 1 ; i <= 10000; i++){
      Player p = new Player("player " + i);//(Player) this.lab03Game.getOrCreatePlayer(playerName);
      for (int j = 1; j <= 100; j++){
        int random, count = 0;
        List<Category> categories = new ArrayList<>();
        int countcategories = rand.nextInt((5-3)+1)+3;
        while (count < countcategories){
          random = rand.nextInt(((resultL.size()-1)-0)+1)+0;
          categories.add(resultL.get(random));
          count++;
        }
        int amountOfQuestionForCategory = rand.nextInt((5-3)+1)+3;

        Game g = (Game) lab03Game.createGame(p,lab03Game.getQuestions(categories, amountOfQuestionForCategory));
        lab03Game.playGame(g);
        p.getGames().add(g);
        categories.clear();
      }
      em.persist(p);
      if(i % 1000 == 0){
        em.flush();
        em.clear();
      }
    }
    em.getTransaction().commit();
    em.close();
    LocalTime end = LocalTime.now();
    long between = ChronoUnit.MILLIS.between(start, end);
    int minutes = (int) ((between/1000) / 60);
    int seconds = (int) ((between/1000) % 60);
    System.out.println("The duration is: "+minutes+" minutes and "+seconds+" seconds");
  }
}
