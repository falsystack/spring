package jp.co.falsystack.tacocloud.data;

import jp.co.falsystack.tacocloud.Ingredient;

public interface IngredientRepository {
    Iterable<Ingredient> findAl();
    Ingredient findById(int id);
    Ingredient save(Ingredient ingredient);
}
