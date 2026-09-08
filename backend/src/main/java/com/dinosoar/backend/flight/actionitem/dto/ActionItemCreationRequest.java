package com.dinosoar.backend.flight.actionitem.dto;

import com.dinosoar.backend.flight.actionitem.note.dto.NoteCreationRequest;
import jakarta.validation.Valid;

import java.util.List;

public record ActionItemCreationRequest(
        String text,
        List<@Valid NoteCreationRequest> notes
) {}
