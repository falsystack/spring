package jp.falsystack.itemservice.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class ItemRepositoryTest {
    ItemRepository itemRepository = new ItemRepository();

    @AfterEach
    void afterEach() {
        itemRepository.clearStore();
    }

    @Test
    @DisplayName("save")
    void save() {
        // given
        Item item = Item.builder()
                .itemName("itemA")
                .price(10000)
                .quantity(10)
                .build();
        // when
        Item savedItem = itemRepository.save(item);

        // then
        Item findItem = itemRepository.findById(savedItem.getId());
        assertThat(findItem).isEqualTo(savedItem);
    }

    @Test
    @DisplayName("findAll")
    void findAll() {
        // given
        Item itemA = Item.builder()
                .itemName("itemA")
                .price(10000)
                .quantity(10)
                .build();

        Item itemB = Item.builder()
                .itemName("itemB")
                .price(20000)
                .quantity(20)
                .build();
        itemRepository.save(itemA);
        itemRepository.save(itemB);
        // when
        List<Item> items = itemRepository.findAll();

        // then
        assertThat(items.size()).isEqualTo(2);
        assertThat(items).contains(itemA, itemB);
    }

    @Test
    @DisplayName("updateItem")
    void updateItem() {
        // given
        Item itemA = Item.builder()
                .itemName("itemA")
                .price(10000)
                .quantity(10)
                .build();
        Item savedItem = itemRepository.save(itemA);

        // when
        Item updateParam = Item.builder()
                .itemName("item1")
                .price(20000)
                .quantity(30)
                .build();
        itemRepository.update(savedItem.getId(), updateParam);
        // then
        Item findItem = itemRepository.findById(savedItem.getId());
        assertThat(findItem.getItemName()).isEqualTo(updateParam.getItemName());
        assertThat(findItem.getPrice()).isEqualTo(updateParam.getPrice());
        assertThat(findItem.getQuantity()).isEqualTo(updateParam.getQuantity());
    }
}