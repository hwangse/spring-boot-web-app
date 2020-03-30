package jpabook.jpashop.service;

import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderStatus;
import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.exception.NotEnoughStockException;
import jpabook.jpashop.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class OrderServiceTest {

    @Autowired EntityManager em;
    @Autowired OrderService orderService;
    @Autowired OrderRepository orderRepository;

    @Test
    public void 상품주문() throws Exception {
        //given
        Member member = createMember("황새");
        Item book = createBook("나쓰미의 반딧불이", 12000, 10);

        //when
        int orderCount = 2;
        Long orderId = orderService.order(member.getId(), book.getId(), orderCount);
        
        //then
        Order order = orderRepository.findOne(orderId);
        assertEquals(OrderStatus.ORDER, order.getStatus());
        assertEquals(1, order.getOrderItems().size());
        assertEquals(8, book.getStockQuantity());
        assertEquals(24000, order.getTotalPrice());
    }

    @Test
    public void 상품주문_재고수량_초과() throws Exception {
        //given
        Member member = createMember("황새");
        Item book = createBook("나쓰미의 반딧불이", 12000, 10);
        
        //when
        int orderCount = 11;
        try{
            orderService.order(member.getId(), book.getId(), orderCount);
        }catch(NotEnoughStockException e){
            return;
        }
        
        //then
        fail("여기에 도달하면 안됨");
    }
    
    @Test
    public void 주문취소() throws Exception {
        //given
        Member member = createMember("황새");
        Item book = createBook("나쓰미의 반딧불이", 12000, 10);

        int orderCount = 3;
        Long orderId = orderService.order(member.getId(), book.getId(), orderCount);

        //when
        orderService.cancelOrder(orderId);
        
        //then
        Order canceledOrder = orderRepository.findOne(orderId);
        assertEquals(OrderStatus.CANCEL, canceledOrder.getStatus());
        assertEquals(10, book.getStockQuantity());
    }

    // private
    private Item createBook(String title, int price, int count) {
        Item book = new Book();
        book.setName(title);
        book.setPrice(price);
        book.setStockQuantity(count);
        em.persist(book);
        return book;
    }

    private Member createMember(String name) {
        Member member = new Member();
        member.setName(name);
        member.setAddress(new Address("서울특별시", "서강대길", "16789"));
        em.persist(member);
        return member;
    }
}