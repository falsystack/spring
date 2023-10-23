package jp.falsystack.tacocloud;

import lombok.Builder;
import lombok.Data;

@Data
public class Ingredient {

  private final String id;
  private final String name;
  private final Type type;

  @Builder
  public Ingredient(String id, String name, Type type) {
    this.id = id;
    this.name = name;
    this.type = type;
  }

  public static enum Type {
    WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
  }

}
