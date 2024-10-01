package com.mysite.sbb.user;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class SiteUser {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    // USERNAME 컬럼에 매핑되도록 수정
    @Column(name = "USER_NAME", unique = true)
    private String userName;
    
    @Column
    private String password;
    
    @Column(unique = true)
    private String email;
}
