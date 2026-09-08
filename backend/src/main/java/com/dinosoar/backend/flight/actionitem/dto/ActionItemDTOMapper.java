package com.dinosoar.backend.flight.actionitem.dto;

import com.dinosoar.backend.flight.actionitem.ActionItem;
import com.dinosoar.backend.flight.actionitem.note.dto.NoteDTO;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class ActionItemDTOMapper implements Function<ActionItem, ActionItemDTO> {

    @Override
    public ActionItemDTO apply(ActionItem actionItem) {
        return new ActionItemDTO(
                actionItem.getText(),
                actionItem
                        .getNotes()
                        .stream()
                        .map(note -> new NoteDTO(note.getText()))
                        .toList()
        );
    }
}
