package com.demoapp.demo.controller;

import com.demoapp.demo.model.User;
import com.demoapp.demo.model.UserType;
import com.demoapp.demo.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.event.annotation.BeforeTestClass;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class AdminControllerTest {

    @Autowired
    @InjectMocks
    AdminController adminController;

    @Autowired
    UserRepository userRepository;

    @BeforeTestClass
    public void deleteAll(){
        userRepository.deleteAll();
    }

    User user = new User();
    @BeforeEach
    public void addUser(){
        user.setUserID(1);
        user.setName("Aviral");
        user.setAge("27");
        user.setUserType(UserType.BUYER);
        adminController.addUser(user);

    }

    @Test
    public void testDisplayAllUser(){
        Assertions.assertNotNull(adminController.displayAllUsers());
        Assertions.assertEquals(1,adminController.displayAllUsers().size());
    }

    @Test
    public void testAddUser(){

        Assertions.assertEquals(1, adminController.displayAllUsers().get(0).getUserID());
    }

    @Test
    public void testGetUserById(){
        user.setUserID(1);
//        Assertions.assertEquals(adminController.getUserById(1).get(), user);
    }

    @Test
    public void testUpdateUser(){

        user.setUserID(1);

        Assertions.assertEquals(adminController.getUserById(1).get().getAge(), "27");

        user.setAge("37");
        adminController.updateUser(1, user);
        Assertions.assertEquals(adminController.getUserById(1).get().getAge(), "37");

    }

    @Test
    public void testDeleteUserById(){
        user.setUserID(1);

        Assertions.assertEquals(adminController.displayAllUsers().size(), 1);

        adminController.deleteUserById(user.getUserID());
        Assertions.assertEquals(adminController.displayAllUsers().size(), 0);
    }
}
