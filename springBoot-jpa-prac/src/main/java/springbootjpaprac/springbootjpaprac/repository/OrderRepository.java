package springbootjpaprac.springbootjpaprac.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import springbootjpaprac.springbootjpaprac.domain.Member;
import springbootjpaprac.springbootjpaprac.domain.Order;
import springbootjpaprac.springbootjpaprac.repository.order.simplequery.SimpleOrderQueryDto;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderRepository {

    private final EntityManager entityManager;

    public void save(Order order){
        entityManager.persist(order);
    }

    public Order findOne(Long id){
        return entityManager.find(Order.class, id);
    }

    public List<Order> findAllByCriteria(OrderSearch orderSearch) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Order> cq = cb.createQuery(Order.class);
        Root<Order> o = cq.from(Order.class);
        Join<Order, Member> m = o.join("member", JoinType.INNER); //회원과 조인
        List<Predicate> criteria = new ArrayList<>();
        //주문 상태 검색
        if (orderSearch.getOrderStatus() != null) {
            Predicate status = cb.equal(o.get("status"),
                    orderSearch.getOrderStatus());
            criteria.add(status);
        }
        //회원 이름 검색
        if (StringUtils.hasText(orderSearch.getMemberName())) {
            Predicate name =
                    cb.like(m.<String>get("name"), "%" +
                            orderSearch.getMemberName() + "%");
            criteria.add(name);
        }
        cq.where(cb.and(criteria.toArray(new Predicate[criteria.size()])));
        TypedQuery<Order> query = entityManager.createQuery(cq).setMaxResults(1000); //최대 100건
        return query.getResultList();
    }

    public List<Order> findAllWithMemberDelivery(){
        List<Order> resultList = entityManager.createQuery(
                "select o from Order o" +
                        " join fetch o.member m" +
                        " join fetch o.delivery d", Order.class
        ).getResultList();

        return  resultList;
    }

    //페이징
    public List<Order> findAllWithMemberDelivery(int offset, int limit){
        List<Order> resultList = entityManager.createQuery(
                "select o from Order o" +
                        " join fetch o.member m" +
                        " join fetch o.delivery d", Order.class)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();

        return  resultList;
    }

//    public List<SimpleOrderQueryDto> findOrderDtos(){       //레포지토리에서 DTO로 조회 후 반환. 재사용성 떨어짐. 하지만 쿼리에서 select 다음 데이터를 별로 안가져와서 성능 쪼금 더 높음
//        List<SimpleOrderQueryDto> resultList = entityManager.createQuery(
//                "select new springbootjpaprac.springbootjpaprac.repository.SimpleOrderQueryDto(o.id, m.name, o.orderDate, o.status, d.address)" +
//                " from Order o" +
//                        " join o.member m" +
//                        " join o.delivery d", SimpleOrderQueryDto.class
//        ).getResultList();
//
//        return  resultList;
//    }

    public List<Order> findAllWithItem(){       //스프링 부트 3.x 이상부터는 조인할 때 distinct 기본으로 넣음.알아서 중복제거
        List<Order> resultList = entityManager.createQuery(
                "select o from Order o" +
                        " join fetch o.member m" +
                        " join fetch o.delivery d" +
                        " join fetch o.orderItems oi" +
                        " join fetch oi.item i", Order.class)
                .setFirstResult(1)
                .setMaxResults(100)
                .getResultList();

        return resultList;
    }
}
