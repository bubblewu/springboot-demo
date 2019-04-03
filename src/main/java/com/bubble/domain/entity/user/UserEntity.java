package com.bubble.domain.entity.user;

import com.bubble.domain.entity.AbstractEntity;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 用户信息
 *
 * @author wugang
 * date: 2019-04-02 19:11
 **/
@Entity
@Component
@Table(name = "user")
public class UserEntity extends AbstractEntity implements Serializable {
    private static final long serialVersionUID = 2857369147639688810L;

//    @Id
//    @GeneratedValue
//    @Column(name = "id")
//    private int id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "pwd", nullable = false)
    private String pwd;

//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }


    @Override
    public String toString() {
        return "UserEntity{" +
                "name='" + name + '\'' +
                ", pwd='" + pwd + '\'' +
                '}';
    }
}
