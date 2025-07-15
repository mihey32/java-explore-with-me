package ru.practicum.ewm.compilations.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.compilations.dto.CompilationDto;
import ru.practicum.ewm.compilations.dto.NewCompilationDto;
import ru.practicum.ewm.compilations.dto.UpdateCompilationRequest;
import ru.practicum.ewm.compilations.service.AdminCompilationService;

@Slf4j
@RequiredArgsConstructor
@RestController
@Validated
@RequestMapping("/admin/compilations")
public class AdminCompilationsController {

    private final AdminCompilationService service;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public CompilationDto save(@RequestBody @Valid NewCompilationDto request) {
        log.info("Получен запрос POST /admin/compilations c новой подборкой: \"{}\"", request.getTitle());
        return service.save(request);
    }

    @PatchMapping("/{comp-id}")
    @ResponseStatus(HttpStatus.OK)
    public CompilationDto update(@PathVariable("comp-id") @Positive Long eventId,
                                 @RequestBody @Valid UpdateCompilationRequest request) {
        log.info("Получен запрос PATCH /admin/compilations/{} на изменение подборки", eventId);
        return service.update(request, eventId);
    }

    @DeleteMapping("/{comp-id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("comp-id") @Positive Long compId) {
        log.info("Получен запрос DELETE /admin/compilations/{}", compId);
        service.delete(compId);
    }
}
