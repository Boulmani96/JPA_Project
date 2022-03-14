package de.hda.fbi.db2.stud.impl;

import de.hda.fbi.db2.api.Lab01Data;
import de.hda.fbi.db2.stud.entity.Category;
import de.hda.fbi.db2.stud.entity.Question;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Lab01Impl extends Lab01Data {
  private List<Question> questions = new ArrayList<>();
  private List<Category> categories = new ArrayList<>();

  @Override
  public List<Question> getQuestions() {
    return questions;
  }

  @Override
  public List<Category> getCategories() {
    return categories;
  }

  @Override
  public void loadCsvFile(List<String[]> additionalCsvLines) {
    HashMap<String, Category> categoryMap = new HashMap<String, Category>();

    for (int i = 1; i < additionalCsvLines.size(); i++) {
      Category category = new Category(additionalCsvLines.get(i)[7]);
      Question question = new Question(Integer.parseInt(additionalCsvLines.get(i)[0]),
          additionalCsvLines.get(i)[1], Integer.parseInt(additionalCsvLines.get(i)[6]));

      question.getAnswerList().add(additionalCsvLines.get(i)[2]);
      question.getAnswerList().add(additionalCsvLines.get(i)[3]);
      question.getAnswerList().add(additionalCsvLines.get(i)[4]);
      question.getAnswerList().add(additionalCsvLines.get(i)[5]);

      questions.add(question);

      if (categoryMap.get(category.getName()) == null) {
        categoryMap.put(additionalCsvLines.get(i)[7], category);
        categories.add(category);
        category.getMyQuestions().add(question);

      } else {
        categoryMap.get(category.getName()).getMyQuestions().add(question);
        //question.setCategory(category);
      }
    }

    System.out.println("Total number of Questions is: " + this.questions.size());
    System.out.println("Total number of categories is: " + this.categories.size());
  }
}
