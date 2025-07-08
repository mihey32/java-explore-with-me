package ru.practicum.ewm.compilations;

import ru.practicum.ewm.compilations.dto.CompilationDto;
import ru.practicum.ewm.compilations.dto.NewCompilationDto;
import ru.practicum.ewm.compilations.dto.UpdateCompilationRequest;
import ru.practicum.ewm.events.Event;
import ru.practicum.ewm.events.EventMapper;

import java.util.List;
import java.util.Set;

public class CompilationMapper {
    public static Compilation mapToEntity(NewCompilationDto request, Set<Event> events) {
        Compilation compilation = new Compilation();
        compilation.setEvents(events);
        if (request.getPinned() == null) {
            compilation.setPinned(Boolean.FALSE);
        } else {
            compilation.setPinned(request.getPinned());
        }

        compilation.setTitle(request.getTitle());

        return compilation;
    }

    public static CompilationDto mapToDto(Compilation entity) {
        CompilationDto dto = new CompilationDto();
        dto.setId(entity.getId());
        dto.setEvents(EventMapper.mapToListShortDto(entity.getEvents()));
        dto.setPinned(entity.getPinned());
        dto.setTitle(entity.getTitle());
        return dto;
    }

    public static Compilation updateFields(Compilation compilation, UpdateCompilationRequest request, Set<Event> events) {
        if (request.hasEvents()) {
            compilation.setEvents(events);
        }

        if (request.hasPinned()) {
            compilation.setPinned(request.getPinned());
        }

        if (request.hasTitle()) {
            compilation.setTitle(request.getTitle());
        }
        return compilation;
    }

    public static List<CompilationDto> mapToListDto(List<Compilation> compilations) {
        return compilations.stream().map(CompilationMapper::mapToDto).toList();
    }
}
