package com.dinosoar.backend.user.role;

import com.dinosoar.backend.enums.RoleType;
import com.dinosoar.backend.user.User;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
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
    private Set<@Valid User> users;

    public Role() {}

    public Role(int id, RoleType type) {
        this.id = id;
        this.type = type;
        this.users = new HashSet<>();
    }

    public void addUser(User user) {
        this.users.add(user);
    }
}
