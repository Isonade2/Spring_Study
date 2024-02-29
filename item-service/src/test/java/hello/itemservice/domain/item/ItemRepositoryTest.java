package hello.itemservice.domain.item;

import hello.itemservice.item.Item;
import hello.itemservice.item.ItemRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class ItemRepositoryTest {

    ItemRepository itemRepository= new ItemRepository();

    @AfterEach
    void afterEach(){
        itemRepository.clearStore();
    }

    @Test
    void save(){
        //given
        Item item = new Item("itemA", 10000, 10);
        //when
        Item savedItem = itemRepository.save(item);
        //then
        itemRepository.findById(item.getId());

        assertThat(savedItem).isEqualTo(item);

    }

    @Test
    void findAll(){
        //given
        Item item1 = new Item("item1", 10000, 10);
        Item item2= new Item("item2", 20000, 20);
        //when
        itemRepository.save(item1);
        itemRepository.save(item2);
        //then
        List<Item> result = itemRepository.findAll();

        assertThat(result.size()).isEqualTo(2);
        assertThat(result).contains(item1,item2);
    }
    @Test
    void updateItem(){
        //given
        Item item1 = new Item("item1", 10000, 10);

        Item savedItem = itemRepository.save(item1);
        Long id = savedItem.getId();
        //when

        Item updateParam = new Item("item2", 20000, 20);
        itemRepository.update(id,updateParam);
        //then
        Item findItem = itemRepository.findById(id);
        assertThat(findItem.getItemName()).isEqualTo(updateParam.getItemName());
        assertThat(findItem.getPrice()).isEqualTo(updateParam.getPrice());
        assertThat(findItem.getQuantity()).isEqualTo(updateParam.getQuantity());

    }
}
