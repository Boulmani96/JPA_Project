package de.hda.fbi.db2.stud.impl;

import de.hda.fbi.db2.api.Lab03Game;
import de.hda.fbi.db2.stud.entity.Category;
import de.hda.fbi.db2.stud.entity.Game;
import de.hda.fbi.db2.stud.entity.Player;
import de.hda.fbi.db2.stud.entity.Question;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

public class Lab03Imp extends Lab03Game {
  Scanner sc = new Scanner(System.in, "UTF-8"); //System.in is a standard input stream.

  @Override
  public Object getOrCreatePlayer(String playerName) {
    try {
      Player p = (Player) lab02EntityManager.getEntityManager().createNamedQuery("player.findByName").setParameter("name",playerName)
          .setHint("eclipselink.query-results-cache", true).getSingleResult();
      return p;
    }catch (NoResultException e){
      return new Player(playerName);
    }
  }

  @Override
  public Object interactiveGetOrCreatePlayer() {
    System.out.println("geben sie Ihre Name");
    String playerName = sc.next();
    return getOrCreatePlayer(playerName);
  }

  @Override
  public List<?> getQuestions(List<?> categories, int amountOfQuestionsForCategory) {
    List<Question> myQuestion = new ArrayList<>();
    for (int i = 0; i < categories.size(); i++){
      Category category = (Category) categories.get(i);
      Random rand = new Random();
      int random;
      if(category.getMyQuestions().size()>amountOfQuestionsForCategory){
        for (int j = 0; j < amountOfQuestionsForCategory; j++){
          random = rand.nextInt(((category.getMyQuestions().size()-1)-0)+1)+0;
          myQuestion.add(category.getMyQuestions().get(random));
        }
      }else {
        for (int j = 0; j < category.getMyQuestions().size(); j++) {
          myQuestion.add(category.getMyQuestions().get(j));
        }
      }
    }
    return myQuestion;
  }

  @Override
  public List<?> interactiveGetQuestions() {
    List<Object> mycategories = new ArrayList<>();

    List resultL = lab02EntityManager.getEntityManager().createQuery("select c from Category c order by c.id").getResultList();
    List<Category> categories = new ArrayList<>();

    for(Iterator i = resultL.iterator();i.hasNext();){
      categories.add((Category) i.next());
    }

    for (int i = 0; i < categories.size(); i++){
      System.out.println(categories.get(i).getId()+" "+categories.get(i).getName());
    }

    boolean exists = false;
    System.out.println("Give the ID of category (-1 to stop)");
    int id = sc.nextInt();
    while (id==-1)
    {
      System.out.println("You have to choice 2 categories ");
      id = sc.nextInt();
    }
    while (id != -1) {

      for(int j = 0; j < categories.size(); j++){
        if(categories.get(j).getId() == id){
          exists = true;
          mycategories.add(categories.get(j));
          break;
        }
      }
      if(exists == false){
        System.out.println("Give the right ID");
      }
      System.out.println("Give the ID of category (-1 to stop)");
      id = sc.nextInt();
      exists = false;
      if(id==-1 && mycategories.size()<2){
        System.out.println("You need to choice one more category");
        id = sc.nextInt();
      }
    }
    System.out.println("Give the number of questions per category.");
    int count = sc.nextInt();
    return this.getQuestions(mycategories, count);
  }

  @Override
  public Object createGame(Object player, List<?> questions) {
    Map<Question, Integer> myMap = new HashMap<>();
    for (int i = 0; i < questions.size(); i++){
      myMap.put((Question) questions.get(i),0);
    }
    Game mYGame = new Game((Player) player,myMap);
    return mYGame;
  }

  @Override
  public void playGame(Object game) {
    Game g = (Game) game;
    LocalDateTime start = LocalDateTime.now();

    LocalDateTime tomorrow = start.plusDays(1);
    g.setStart(tomorrow);
    int random;
    for(Question key : g.getMyQuetionMap().keySet()){
      //System.out.println(key.getText());
     for (int j = 0; j < key.getAnswerList().size(); j++) {
      // System.out.println(key.getAnswerList().get(j));
      }
     random = (int) (Math.random()*4+1);
     g.getMyQuetionMap().replace(key,random);
     if(random == (key.getCorrectAnswerIndex())){
       //System.out.println("Your answer " + random + " is correct  ");
     }else{
       // System.out.println("Your answer " + random + " is not correct  ");
      }
   }

    LocalDateTime end = LocalDateTime.now();
    LocalDateTime tomorrow1 = end.plusDays(1);
    g.setEnd(tomorrow1);
  }

  @Override
  public void interactivePlayGame(Object game) {
    Game g = (Game) game;
    LocalDateTime start = LocalDateTime.now();
    g.setStart(start);

    for(Question key : g.getMyQuetionMap().keySet()){
      System.out.println(key.getText());
      for (int j = 0; j < key.getAnswerList().size(); j++) {
       System.out.println(key.getAnswerList().get(j));
     }
      System.out.println("Your answer ?");
      int answer = sc.nextInt();
      g.getMyQuetionMap().replace(key,answer);
      if(answer == (key.getCorrectAnswerIndex())){
        System.out.println("Your answer" + answer + " is correct ");
      }else{
        System.out.println("Your answer " + answer + " is not correct  ");
      }
    }
    LocalDateTime end = LocalDateTime.now();
    g.setEnd(end);
  }

  @Override
  public void persistGame(Object game) {
    EntityManager em = this.lab02EntityManager.getEntityManager();
    em.getTransaction().begin();

    Game g = (Game) game;
    em.persist(g);
    //em.persist(g.getPlayer());
    em.getTransaction().commit();
    em.close();
  }
}
