package ru.practicum.ewm.compilations.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.ewm.compilations.Compilation;
import ru.practicum.ewm.compilations.CompilationMapper;
import ru.practicum.ewm.compilations.CompilationRepository;
import ru.practicum.ewm.compilations.dto.CompilationDto;
import ru.practicum.ewm.exceptions.NotFoundException;

import java.util.Collection;
import java.util.List;

@Slf4j
@Service
public class PublicCompilationsServiceImpl implements PublicCompilationsService {
    private final CompilationRepository compilationRepository;

    public PublicCompilationsServiceImpl(CompilationRepository compilationRepository) {
        this.compilationRepository = compilationRepository;
    }

    public Compilation findById(Long compId) {
        return compilationRepository.findById(compId)
                .orElseThrow(() -> new NotFoundException(String.format("Подборка c ID %d не найдена", compId)));
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<CompilationDto> getAll(Boolean pinned, Integer from, Integer size) {
        PageRequest pageRequest = PageRequest.of(from / size, size, Sort.by(Sort.Direction.ASC, "id"));

        List<Compilation> compilations;
        if (pinned != null) {
            compilations = compilationRepository.findAllByPinned(pinned, pageRequest);
        } else {
            compilations = compilationRepository.findAll(pageRequest).toList();
        }

        return CompilationMapper.mapToListDto(compilations);
    }

    @Override
    @Transactional(readOnly = true)
    public CompilationDto get(Long compId) {
        return CompilationMapper.mapToDto(findById(compId));
    }

}
