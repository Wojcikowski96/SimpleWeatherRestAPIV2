package com.example.task.users;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "PRIVILAGES")
@RequiredArgsConstructor
@Getter
public class Privilage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "privileges")
    private Collection<Role> roles;

    public Privilage(String name) {
        this.name = name;
    }
}
