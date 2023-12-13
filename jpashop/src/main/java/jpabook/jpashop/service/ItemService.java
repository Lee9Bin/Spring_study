package jpabook.jpashop.service;

import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;

    @Transactional
    public void saveItem(Item item){
        itemRepository.save(item);
    }

    @Transactional //어노테이션 공부하기 -> 트랜잭션얼 어노테이션 끝에서는 커밋이 일어나고
    // jpa는 flush를 날려-> 영속성 컨텍스트에 있는것중에 변경된게 뭔지 다 찾고 업데이트 처리를 한다
    public void updateItem(Long itemId, Book param){
        Item findItem = itemRepository.findOne(itemId);
        findItem.setPrice(param.getPrice());
        findItem.setName(param.getName());
        findItem.setStockQuantity(param.getStockQuantity());
        // itemRepository.save(findItem); -> 실행하지 않아도 돼 왜냐 영속성 객체는 변경감지가 일어나
    }

    public List<Item> findItem(){
        return itemRepository.findAll();
    }

    public Item findOne(Long itemId){
        return itemRepository.findOne(itemId);
    }
}
