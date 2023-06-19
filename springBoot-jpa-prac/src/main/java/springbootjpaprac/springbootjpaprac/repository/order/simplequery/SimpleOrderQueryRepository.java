package springbootjpaprac.springbootjpaprac.repository.order.simplequery;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class SimpleOrderQueryRepository {   //api 스펙에 맞춘 코드는 레포지토리에 바로 넣지 말고, 따로 이렇게 패키지 만들어서 관리하기

    private final EntityManager entityManager;

    public List<SimpleOrderQueryDto> findOrderDtos(){
        List<SimpleOrderQueryDto> resultList = entityManager.createQuery(
                "select new springbootjpaprac.springbootjpaprac.repository.order.simplequery.SimpleOrderQueryDto(o.id, m.name, o.orderDate, o.status, d.address)" +
                        " from Order o" +
                        " join o.member m" +
                        " join o.delivery d", SimpleOrderQueryDto.class
        ).getResultList();

        return  resultList;
    }
}
