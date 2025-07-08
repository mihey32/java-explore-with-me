package ru.practicum.ewm.compilations.service;

import ru.practicum.ewm.compilations.dto.CompilationDto;

import java.util.Collection;

public interface PublicCompilationsService {
    Collection<CompilationDto> getAll(Boolean pinned, Integer from, Integer size);

    CompilationDto get(Long compId);
}
