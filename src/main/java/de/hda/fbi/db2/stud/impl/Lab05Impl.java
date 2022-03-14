package de.hda.fbi.db2.stud.impl;

import de.hda.fbi.db2.api.Lab05AnalyzeData;
import de.hda.fbi.db2.stud.entity.Category;
import de.hda.fbi.db2.stud.entity.Player;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import javax.persistence.EntityManager;

public class Lab05Impl extends Lab05AnalyzeData {
  @Override
  public void analyzeData() {
    try {
      Scanner sc = new Scanner(System.in, "UTF-8");
      int input;
      System.out.println("Please choose which query do you want to run (-1 to stop)");
      System.out.println("1. Ausgabe aller Spieler (Spielername), die in einem bestimmten Zeitraum gespielt hatten.");
      System.out.println("2. Ausgabe zu einem bestimmten Spieler: Alle Spiele (Id, Datum), sowie die Anzahl der korrekten Antworten pro Spiel\n"
          + "mit Angabe der Gesamtanzahl der Fragen pro Spiel bzw. alternativ den Prozentsatz der korrekt beantworteten\n"
          + "Fragen.");
      System.out.println("3. Ausgabe aller Spieler mit Anzahl der gespielten Spiele, nach Anzahl absteigend geordnet.");
      System.out.println("4. Ausgabe der am meisten gefragten Kategorie, oder alternativ, die Beliebtheit der Kategorien nach Anzahl der\n"
          + "Auswahl absteigend sortiert.");

      input = sc.nextInt();
      while (input != -1){
        switch (input){
          case 1:
            Query1();
            break;
          case 2:
            Query2();
            break;
          case 3:
            Query3();
            break;
          case 4:
            Query4();
            break;
          default:
            System.out.println("Input Error");
            break;
        }
        System.out.println("Please choose which query do you want to run (-1 to stop)");
        System.out.println("1. Ausgabe aller Spieler (Spielername), die in einem bestimmten Zeitraum gespielt hatten.");
        System.out.println("2. Ausgabe zu einem bestimmten Spieler: Alle Spiele (Id, Datum), sowie die Anzahl der korrekten Antworten pro Spiel\n"
            + "mit Angabe der Gesamtanzahl der Fragen pro Spiel bzw. alternativ den Prozentsatz der korrekt beantworteten Fragen.");
        System.out.println("3. Ausgabe aller Spieler mit Anzahl der gespielten Spiele, nach Anzahl absteigend geordnet.");
        System.out.println("4. Ausgabe der am meisten gefragten Kategorie, oder alternativ, die Beliebtheit der Kategorien nach Anzahl der\n"
            + "Auswahl absteigend sortiert.");
        input = sc.nextInt();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private void Query1(){
    EntityManager em = this.lab02EntityManager.getEntityManager();
    String Query = "select distinct g.player from Game g where g.startTime >= '2021-07-07 11:07:55.516367+02' and g.endTime <= '2021-07-10 11:07:58.516367+02'";
    List resultL = em.createQuery(Query).getResultList();
    for(Iterator i = resultL.iterator(); i.hasNext();){
      Player p = (Player) i.next();
      System.out.println("Player Name: "+p.getName());
    }
    System.out.println("-------------------------------------------------------------------------------------------------------");
  }

  private void Query2(){
    EntityManager em = this.lab02EntityManager.getEntityManager();
    String Query = "select g.id, g.startTime, g.endTime, (select count(gp) from g.myquestionmap gp where  KEY(gp).correctAnswerIndex=VALUE(gp) ), size(g.myquestionmap) "
        + "from Game g where g.player.name='player 1' ";
    List resultL = em.createQuery(Query).getResultList();
    for(Iterator i = resultL.iterator(); i.hasNext();){
      Object[] element = (Object[]) i.next();
      System.out.println("GameID: "+element[0]+" , StartTime: "+element[1]+" , EndTime: "+element[2]+" , Number of correct answers: "+element[3]+" , Total Questions: "+element[4]);
    }
    System.out.println("-------------------------------------------------------------------------------------------------------");
  }

  private void Query3(){
    EntityManager em = this.lab02EntityManager.getEntityManager();
    String Query = "select g.player.name, count(g.id) as AnzahlSpiele from Game g group by g.player.name order by AnzahlSpiele";
    List resultL = em.createQuery(Query).getResultList();
    for(Iterator i = resultL.iterator(); i.hasNext();){
      Object[] element = (Object[]) i.next();
      System.out.println("Player name: "+element[0]+" , Number of games played: "+element[1]);
    }
    System.out.println("--------------------------------------------------------------------------------------------------------");
  }

  private void Query4(){
    EntityManager em = this.lab02EntityManager.getEntityManager();
    String Query = "select c.id, count (DISTINCT g.id) "
        + "from Game g, g.myquestionmap gm, Category c, c.myQuestions cq "
        + "where value(cq).id=key(gm).id group by c.id order by count (DISTINCT g.id) ";

    List resultL = em.createQuery(Query).getResultList();
    for(Iterator i = resultL.iterator(); i.hasNext();){
      Object[] element = (Object[]) i.next();
      System.out.println("CategoryID: "+element[0]+" , Number: "+element[1]);
    }

    System.out.println("--------------------------------------------------------------------------------------------------------");
  }
}
