package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class OrderSearch { // 주문 검색을 위한 파라미터

    private String memberName;
    private OrderStatus orderStatus;
}
