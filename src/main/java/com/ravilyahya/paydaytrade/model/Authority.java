package com.ravilyahya.paydaytrade.model;


import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

@Getter
@Setter
public class Authority implements GrantedAuthority {

    private String authority;

    public Authority(String authority){
        this.authority=authority;
    }


}
