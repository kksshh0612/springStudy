package springbootjpaprac.springbootjpaprac.repository;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import springbootjpaprac.springbootjpaprac.domain.items.Item;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    private final EntityManager entityManager;

    public void save(Item item){
        if(item.getId() == null){
            entityManager.persist(item);
        }
        else{
            entityManager.merge(item);      //update같은 것
        }
    }

    public Item findOne(Long id){
        return entityManager.find(Item.class, id);
    }

    public List<Item> findAll(){
        String query = "select i from Item i";

        List<Item> resultList = entityManager.createQuery(query, Item.class)
                .getResultList();

        return resultList;
    }
}
