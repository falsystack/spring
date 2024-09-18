package jp.co.falsystack.tacocloud.data;

import jp.co.falsystack.tacocloud.Ingredient;
import jp.co.falsystack.tacocloud.Taco;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.sql.Types;
import java.util.Arrays;
import java.util.Date;

@RequiredArgsConstructor
@Repository
public class JdbcTacoRepository implements TacoRepository {

    private final JdbcTemplate jdbc;

    @Override
    public Taco save(Taco taco) {
        var tacoId = saveTacoInfo(taco);
        taco.setId(tacoId);
        for (var ingredient : taco.getIngredients()) {
            saveIngredientToTaco(ingredient, tacoId);
        }
        return null;
    }

    private void saveIngredientToTaco(Ingredient ingredient, long tacoId) {
        jdbc.update(
                "insert into Taco_Ingredients (taco, ingredient) values (?, ?)",
                tacoId,
                ingredient.getId()
        );
    }

    private long saveTacoInfo(Taco taco) {
        taco.setCreatedAt(new Date());
        var psc = new PreparedStatementCreatorFactory(
                "insert into Taco(name, createdAt) values (?,?)",
                Types.VARCHAR, Types.TIMESTAMP
        ).newPreparedStatementCreator(
                Arrays.asList(taco.getName(), new Timestamp(taco.getCreatedAt().getTime())
                ));

        var keyHolder = new GeneratedKeyHolder();
        jdbc.update(psc, keyHolder);
        return keyHolder.getKey().longValue();
    }
}
