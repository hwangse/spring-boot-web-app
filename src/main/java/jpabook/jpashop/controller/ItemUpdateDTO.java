package jpabook.jpashop.controller;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ItemUpdateDTO {

    private String name;
    private int price;
    private int stockQuantity;
}
