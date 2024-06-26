package hello.itemservice.domain.item;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class ItemRepositoryTest {
    ItemRepository itemRepository = new ItemRepository();

    @AfterEach
    void afterEach(){
        itemRepository.clearStore();
    }

    @Test
    void save(){
        //given
        Item item = new Item("item1", 10000, 4);

        //when
        Item saveItem = itemRepository.save(item);

        //then
        Item findItem = itemRepository.findBy(item.getId());
        assertThat(findItem).isEqualTo(saveItem);

    }
    @Test
    void findAll(){
        //given
        Item item1 = new Item("item1", 20000, 14);
        Item item2 = new Item("item2", 10000, 4);

        itemRepository.save(item1);
        itemRepository.save(item2);

        //when
        List<Item> result = itemRepository.findAll();

        //then
        assertThat(result.size()).isEqualTo(2);
        assertThat(result).contains(item1, item2);
    }

    @Test
    void updateItem(){
        //given
        Item item = new Item("item1", 20000, 14);
        Item savedItem = itemRepository.save(item);
        Long id = savedItem.getId();

        //when
        Item updateParam = new Item("item2", 10000, 10);
        itemRepository.update(id, updateParam);

        //then
        Item findItem = itemRepository.findBy(id);
        assertThat(findItem.getItemName()).isEqualTo(updateParam.getItemName());
        assertThat(findItem.getPrice()).isEqualTo(updateParam.getPrice());
        assertThat(findItem.getQuantity()).isEqualTo(updateParam.getQuantity());
    }
}