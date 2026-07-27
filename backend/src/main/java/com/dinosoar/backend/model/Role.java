package com.dinosoar.backend.model;

import com.dinosoar.backend.enums.RoleType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "roles")
@Getter
@Setter
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    @Enumerated(EnumType.STRING)
    private RoleType type;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

    public void addUser(User user) {
        this.users.add(user);
    }
}
