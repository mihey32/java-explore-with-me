package ru.practicum.ewm.compilations.controller;

import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.compilations.dto.CompilationDto;
import ru.practicum.ewm.compilations.service.PublicCompilationsService;

import java.util.Collection;

@Slf4j
@RequiredArgsConstructor
@RestController
@Validated
@RequestMapping("/compilations")
public class PublicCompilationsController {

    private final PublicCompilationsService service;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Collection<CompilationDto> getAll(@RequestParam(defaultValue = "false") Boolean pinned,
                                             @RequestParam(defaultValue = "0") Integer from,
                                             @RequestParam(defaultValue = "10") Integer size) {
        log.info("Получен запрос GET /compilations с параметрами from = {} size = {}", from, size);
        return service.getAll(pinned, from, size);
    }

    @GetMapping("/{comp-id}")
    @ResponseStatus(HttpStatus.OK)
    public CompilationDto getCompilation(@PathVariable("comp-id") @Positive Long compId) {
        log.info("Получен запрос GET /compilations/{}", compId);
        return service.get(compId);
    }
}
