package de.hda.fbi.db2.stud.entity;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OrderColumn;
import javax.persistence.Table;

@Entity
@Table(name = "Question", schema = "public")
public class Question {
  @Id
  private int id;
  private String text;
  private int correctAnswerIndex;
  @ElementCollection
  @OrderColumn
  private List<String> answerList;

  public Question() {
  }

  public Question(int id, String text, int correctAnswerIndex) {
    this.id = id;
    this.text = text;
    this.correctAnswerIndex = correctAnswerIndex;
    this.answerList = new ArrayList<>(4);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Question question = (Question) o;
    return id == question.id;
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public List<String> getAnswerList() {
    return answerList;
  }

  public void setAnswerList(List<String> answerList) {
    this.answerList = answerList;
  }

  public int getCorrectAnswerIndex() {
    return correctAnswerIndex;
  }

  public void setCorrectAnswerIndex(int correctAnswerIndex) {
    this.correctAnswerIndex = correctAnswerIndex;
  }

  @Override
  public String toString() {
    String ausgabe = this.id + "," + this.getText() + ", ";
    for (int i = 0; i < this.answerList.size(); i++) {
      ausgabe += " " + this.answerList.get(i) + ", ";
    }
    ausgabe += " " + " " + this.correctAnswerIndex;
    return ausgabe;
  }
}
