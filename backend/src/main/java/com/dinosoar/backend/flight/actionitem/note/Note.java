package com.dinosoar.backend.flight.actionitem.note;

import com.dinosoar.backend.resource.Resource;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "notes")
@Getter
@Setter
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String text;

    @OneToMany
    @JoinTable(
            name = "notes_resources",
            joinColumns = @JoinColumn(name = "notes_id"),
            inverseJoinColumns = @JoinColumn(name = "resource_id")
    )
    private List<@Valid Resource> resources;

    public Note() { }

    public Note(String text, List<Resource> resources) {
        this.text = text;
        this.resources = resources;
    }
}
