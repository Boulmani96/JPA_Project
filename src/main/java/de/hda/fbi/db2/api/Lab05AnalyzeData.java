package de.hda.fbi.db2.api;

public abstract class Lab05AnalyzeData {

  public void init() {
  }

  /**
   * You can use the data from lab01, this variable will be automatically set.
   */
  protected Lab01Data lab01Data;

  /**
   * You can use the EntityManager or other stuff from lab02, this variable will be automatically
   * set.
   */
  protected Lab02EntityManager lab02EntityManager;

  /**
   * You can use the Lab03Game, this variable will be automatically set.
   */
  protected Lab03Game lab03Game;

  /**
   * You can use the Lab04MassData, this variable will be automatically set.
   */
  protected Lab04MassData lab04MassData;

  /**
   * Setter for Lab01Data.
   * @param lab01Data lab01Data
   */
  public void setLab01Data(Lab01Data lab01Data) {
    this.lab01Data = lab01Data;
  }

  /**
   * Setter fro Lab02EntityManager.
   *
   * @param lab02EntityManager lab02EntityManager
   */
  public void setLab02EntityManager(Lab02EntityManager lab02EntityManager) {
    this.lab02EntityManager = lab02EntityManager;
  }

  /**
   * Setter fro lab03Game.
   *
   * @param lab03Game lab03Game
   */
  public void setLab03Game(Lab03Game lab03Game) {
    this.lab03Game = lab03Game;
  }

  /**
   * Setter fro lab04MassData.
   *
   * @param lab04MassData lab04MassData
   */
  public void setLab04MassData(Lab04MassData lab04MassData){
    this.lab04MassData = lab04MassData;
  }

  public abstract void analyzeData();
}
