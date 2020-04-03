package jpabook.jpashop.controller;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter @Setter
public class MemberForm {
    // 회원 가입시 입력할 form data
    @NotEmpty(message = "이름은 필수 입력 사항입니다.")
    private String name;

    private String city;
    private String street;
    private String zipcode;
}
