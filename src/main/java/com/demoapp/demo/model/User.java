package com.demoapp.demo.model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(	name = "users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username"),
                @UniqueConstraint(columnNames = "email")
        })
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 20)
    private String username;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    @NotBlank
    @Size(max = 120)
    private String password;

//    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
//    @JoinTable(name = "user_roles",
//    joinColumns = @JoinColumn(name="user_id"),
//    inverseJoinColumns = @JoinColumn(name="role_id"))
    @NotBlank
    @Enumerated(EnumType.STRING)
    private UserTypes userType;

    public User() {
    }

    public User(String username, String email, String password, UserTypes userType) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.userType = userType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserType(UserTypes userType) {
        this.userType = userType;
    }

    public UserTypes getUserType() {
        return userType;
    }
}