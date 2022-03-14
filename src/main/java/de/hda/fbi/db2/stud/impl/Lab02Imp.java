package de.hda.fbi.db2.stud.impl;

import de.hda.fbi.db2.api.Lab02EntityManager;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PrePersist;

public class Lab02Imp extends Lab02EntityManager {

  @Override
  public void persistData() {
    EntityManager em = getEntityManager();
    EntityTransaction tx = em.getTransaction();
    tx.begin();
    for (int j = 0; j < lab01Data.getCategories().size(); j++) {
      em.persist(lab01Data.getCategories().get(j));
    }
    for (int i = 0; i < lab01Data.getQuestions().size(); i++) {
      em.persist(lab01Data.getQuestions().get(i));
    }
    tx.commit();
    em.close();
  }

  @Override
  public EntityManager getEntityManager() {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("default-postgresPU");
    EntityManager em = null;
    try {
      em = emf.createEntityManager();
    } catch (RuntimeException e) {
      throw e;
    }
    return em;
  }
}