package ru.practicum.ewm.compilations.service;

import ru.practicum.ewm.compilations.dto.CompilationDto;
import ru.practicum.ewm.compilations.dto.NewCompilationDto;
import ru.practicum.ewm.compilations.dto.UpdateCompilationRequest;

public interface AdminCompilationService {
    CompilationDto save(NewCompilationDto request);

    CompilationDto update(UpdateCompilationRequest request, Long compId);

    void delete(Long compId);
}
