package jp.co.falsystack.tacocloud.data;

import jp.co.falsystack.tacocloud.Ingredient;

public interface IngredientRepository {
    Iterable<Ingredient> findAl();
    Ingredient findById(String id);
    Ingredient save(Ingredient ingredient);
}
