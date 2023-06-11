package com.example.demo.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "account")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AccountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotEmpty(message = "Username is required")
    @Column(name = "user_name")
    private String userName;

    @NotEmpty(message = "Password is required")
    @Column(name = "pass_word")
    private String passWord;

    //    @NotEmpty(message = "First Name is required")
    @Column(name = "first_name")
    private String firstName;

    //    @NotEmpty(message = "Last Name is required")
    @Column(name = "last_name")
    private String lastName;

    @Column(name = "role")
    private int role;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CartEntity> cartEntities;
    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<orderEntity> orderEntities;

    @Override
    public String toString() {
        return "AccountEntity{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", role=" + role +
                '}';
    }
}
