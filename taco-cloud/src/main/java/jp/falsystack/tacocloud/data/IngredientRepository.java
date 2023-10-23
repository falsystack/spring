package jp.falsystack.tacocloud.data;

import jp.falsystack.tacocloud.Ingredient;

public interface IngredientRepository {

  Iterable<Ingredient> findAll();

  Ingredient findById(Long id);

  Ingredient save(Ingredient ingredient);

}
