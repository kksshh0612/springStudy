package springbootjpaprac.springbootjpaprac.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springbootjpaprac.springbootjpaprac.domain.items.Book;
import springbootjpaprac.springbootjpaprac.domain.items.Item;
import springbootjpaprac.springbootjpaprac.repository.ItemRepository;

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

    @Transactional      //변경감지
    public void updateItem(Long itemId, String name, int price, int stockQuantity){
        Item findItem = itemRepository.findOne(itemId);
        //실제로 이렇게 하면 안됨. change(bookParam.getPrice(), bookParam.getName(), bookParam.getStockQuantity())
        //이런식으로 의미있는 매서드를 만들어서 해야됨. 세터 쓰지 말기
        findItem.setName(name);
        findItem.setPrice(price);
        findItem.setStockQuantity(stockQuantity);


    }

    public List<Item> findAllItems(){
        return itemRepository.findAll();
    }

    public Item findOne(Long id){
        return itemRepository.findOne(id);
    }
}
