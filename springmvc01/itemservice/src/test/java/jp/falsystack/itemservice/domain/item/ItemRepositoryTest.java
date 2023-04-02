package jp.falsystack.itemservice.domain.item;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ItemRepositoryTest {

  ItemRepository itemRepository = new ItemRepository();

  @AfterEach
  void afterEach() {
    itemRepository.clearStore();
  }

  @Test
  @DisplayName("")
  void save() {
    // given
    Item item = new Item("itemA", 10000, 10);

    // when(when 절에는 test 의 목적이 와야한다.)
    Item savedItem = itemRepository.save(item);

    // then
    Item findItem = itemRepository.findById(savedItem.getId());

    assertThat(findItem).isEqualTo(savedItem);
  }

  @Test
  @DisplayName("")
  void findAll() {
    // given
    Item item1 = new Item("item1", 10000, 10);
    Item item2 = new Item("item2", 20000, 20);

    itemRepository.save(item1);
    itemRepository.save(item2);

    // when(when 절에는 test 의 목적이 와야한다.)
    List<Item> items = itemRepository.findAll();

    // then
    assertThat(items.size()).isEqualTo(2);
    assertThat(items).contains(item1, item2);
  }

  @Test
  @DisplayName("")
  void updateItem() {
    // given
    Item item1 = new Item("item1", 10000, 10);

    Item savedItem = itemRepository.save(item1);
    Long itemId = savedItem.getId();

    // when(when 절에는 test 의 목적이 와야한다.)
    Item updateParam = new Item("item2", 20000, 30);
    itemRepository.update(itemId, updateParam);

    // then
    Item findItem = itemRepository.findById(itemId);

    assertThat(findItem.getItemName()).isEqualTo(updateParam.getItemName());
    assertThat(findItem.getPrice()).isEqualTo(updateParam.getPrice());
    assertThat(findItem.getQuantity()).isEqualTo(updateParam.getQuantity());
  }
}