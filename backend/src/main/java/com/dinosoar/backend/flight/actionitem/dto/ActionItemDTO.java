package com.dinosoar.backend.flight.actionitem.dto;

import com.dinosoar.backend.flight.actionitem.note.dto.NoteDTO;

import java.util.List;

public record ActionItemDTO(
        String text,
        List<NoteDTO> notes
) {
}
