package com.demoapp.demo.model;

import org.springframework.context.annotation.Lazy;
import org.springframework.data.repository.cdi.Eager;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class UserType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column

    public UserTypes userType;

    public UserType(){

    }

    public UserType(String userType){
        if(userType.equals(UserTypes.DOCTOR.name())){
            this.userType = UserTypes.DOCTOR;
        }else if(userType.equals(UserTypes.PATIENT.name())){
            this.userType = UserTypes.PATIENT;
        }
    }
    public UserType(UserTypes userType){
        this.userType = userType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UserTypes getUserTypes() {
        return userType;
    }

    public void setUserTypes(UserTypes userTypes) {
        this.userType = userTypes;
    }

    @Override
    public String toString() {
        return
                userType.name()
               ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserType)) return false;
        UserType userType1 = (UserType) o;
        return userType == userType1.userType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userType);
    }
}