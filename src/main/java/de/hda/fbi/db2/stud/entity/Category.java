package de.hda.fbi.db2.stud.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "Category", schema = "public")
public class Category {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "public.id_category")
  @SequenceGenerator(name = "public.id_category", sequenceName = "public.id_category", initialValue = 1, allocationSize = 1000)
  private int id;
  @Column(name = "name", unique = true)
  private String name;
  @OneToMany(fetch = FetchType.EAGER)
  @JoinColumn(name = "Category_id", nullable = false)
  private List<Question> myQuestions;

  public Category() { }

  public Category(String name) {
    this.name = name;
    myQuestions = new ArrayList<>();
  }

  public List<Question> getMyQuestions() {
    return myQuestions;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }



  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Category category = (Category) o;
    return id == category.id;
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }



  public void setMyQuestions(List<Question> myQuestions) {
    this.myQuestions = myQuestions;
  }

  @Override
  public String toString() {
    String ss = "Category: "+this.getName();
    for(int i = 0; i < this.getMyQuestions().size(); i++){
      ss += this.getMyQuestions().get(i).toString();
    }
    return ss;
  }
}

