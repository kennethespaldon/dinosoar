package com.dinosoar.backend.flight.actionitem;

import com.dinosoar.backend.flight.Flight;
import com.dinosoar.backend.flight.actionitem.note.Note;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "action_items")
@Getter
@Setter
public class ActionItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String text;

    @ManyToOne
    @JoinColumn(name = "flight_id")
    @Valid
    private Flight flight;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "action_item_id")
    private List<@Valid Note> notes;

    public ActionItem() {}

    public ActionItem(String text, List<Note> notes) {
        this.text = text;
        this.notes = notes;
    }
}
