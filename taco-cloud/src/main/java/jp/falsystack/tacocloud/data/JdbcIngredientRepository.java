package jp.falsystack.tacocloud.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import jp.falsystack.tacocloud.Ingredient;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class JdbcIngredientRepository implements IngredientRepository {

  private final JdbcTemplate jdbc;

  @Override
  public Iterable<Ingredient> findAll() {
    return jdbc.query("select id, name, type from Ingredient", this::mapRowToIngredient);
  }

  @Override
  public Ingredient findById(Long id) {
    return jdbc.queryForObject("select id, name, type from Ingredient where id = ?",
        this::mapRowToIngredient, id);
  }

  @Override
  public Ingredient save(Ingredient ingredient) {
    jdbc.update("insert into Ingredient(id, name, type) values (?, ?, ?,)",
        ingredient.getId(),
        ingredient.getName(),
        ingredient.getType().toString()
    );
    return ingredient;
  }

  private Ingredient mapRowToIngredient(ResultSet rs, int rowNum) throws SQLException {
    return Ingredient.builder()
        .id(rs.getString("id"))
        .name(rs.getString("name"))
        .type(Ingredient.Type.valueOf(rs.getString("type")))
        .build();
  }
}
