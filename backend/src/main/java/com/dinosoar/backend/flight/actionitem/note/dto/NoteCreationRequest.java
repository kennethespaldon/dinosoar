package com.dinosoar.backend.flight.actionitem.note.dto;

import java.util.List;

public record NoteCreationRequest(
        String text,
        List<Long> resourceIds
) {}
