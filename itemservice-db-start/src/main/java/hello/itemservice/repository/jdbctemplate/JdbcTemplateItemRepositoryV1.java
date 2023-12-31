package hello.itemservice.repository.jdbctemplate;

import hello.itemservice.domain.Item;
import hello.itemservice.repository.ItemRepository;
import hello.itemservice.repository.ItemSearchCond;
import hello.itemservice.repository.ItemUpdateDto;
import java.util.List;
import java.util.Optional;
import javax.sql.DataSource;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;

/**
 * JdbcTemplate
 */
public class JdbcTemplateItemRepositoryV1 implements ItemRepository {

  private final JdbcTemplate template;

  public JdbcTemplateItemRepositoryV1(DataSource dataSource) {
    this.template = new JdbcTemplate(dataSource);
  }

  @Override
  public Item save(Item item) {
    var sql = "insert into item(item_name, price, quantity) values(?,?,?)";

    var keyHolder = new GeneratedKeyHolder();
    template.update(conn -> {
      var ps = conn.prepareStatement(sql, new String[]{"id"});
      ps.setString(1, item.getItemName());
      ps.setInt(2, item.getPrice());
      ps.setInt(3, item.getQuantity());
      return ps;
    }, keyHolder);

    var key = keyHolder.getKey().longValue();
    item.setId(key);

    return item;
  }

  @Override
  public void update(Long itemId, ItemUpdateDto updateParam) {
    var sql = "update item set item_name=?, price=?, quantity=? where id = ?";
    template.update(sql,
        updateParam.getItemName(),
        updateParam.getPrice(),
        updateParam.getQuantity(),
        itemId
    );
  }

  @Override
  public Optional<Item> findById(Long id) {
    var sql = "select id, item_name, price, quantity from item where id = ?";

    try {
      var item = template.queryForObject(sql, itemRowMapper(), id);
      return Optional.of(item);
    } catch (EmptyResultDataAccessException e) {
      return Optional.empty();
    }
  }

  private RowMapper<Item> itemRowMapper() {
    return ((rs, rowNum) -> {
      var item = new Item();
      item.setId(rs.getLong("id"));
      item.setItemName(rs.getString("item_name"));
      item.setPrice(rs.getInt("price"));
      item.setQuantity(rs.getInt("quantity"));
      return item;
    });
  }

  @Override
  public List<Item> findAll(ItemSearchCond cond) {
    var itemName = cond.getItemName();
    var maxPrice = cond.getMaxPrice();
    var sql = "select id, item_name, price, quantity from item";

    // 동적쿼리 생략

    return template.query(sql, itemRowMapper());
  }
}
