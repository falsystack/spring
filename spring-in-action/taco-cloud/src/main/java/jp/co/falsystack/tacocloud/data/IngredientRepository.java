package jp.co.falsystack.tacocloud.data;

import jp.co.falsystack.tacocloud.Ingredient;
import org.springframework.data.repository.CrudRepository;

public interface IngredientRepository extends CrudRepository<Ingredient, String> {
}
