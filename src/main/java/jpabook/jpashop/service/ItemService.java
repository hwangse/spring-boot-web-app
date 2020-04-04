package jpabook.jpashop.service;

import jpabook.jpashop.controller.ItemUpdateDTO;
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

    // 저장
    @Transactional
    public void saveItem(Item item){
        itemRepository.save(item);
    }

    // 수정 (업데이트)
    @Transactional
    public void updateItem(Long itemId, ItemUpdateDTO dto){
        Item item = itemRepository.findOne(itemId);

        item.setName(dto.getName());
        item.setPrice(dto.getPrice());
        item.setStockQuantity(dto.getStockQuantity());
    }

    // 조회
    public Item findOne(Long id){
        return itemRepository.findOne(id);
    }

    public List<Item> findItems(){
        return itemRepository.findAll();
    }
}
