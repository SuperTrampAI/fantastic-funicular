package com.github.supertrampai.fantasticfunicular.domain;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "user")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String password;
    private Integer age;
    private Integer sex;
    private String nickname;
    private Integer usertype;
    private Integer status;
    private Date  createdt;
    private Date updatedt;

    public User(String name, String password, Integer age, Integer sex, String nickname, Integer usertype) {
        this.name = name;
        this.password = password;
        this.age = age;
        this.sex = sex;
        this.nickname = nickname;
        this.usertype = usertype;
    }
    public User(){}

    public User(String name, Integer age) {
        this.name = name;
        this.age = age;
    }
}
