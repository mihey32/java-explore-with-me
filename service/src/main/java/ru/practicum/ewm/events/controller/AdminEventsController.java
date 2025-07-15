package ru.practicum.ewm.events.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.enums.States;
import ru.practicum.ewm.events.dto.EventFullDto;
import ru.practicum.ewm.events.dto.NewParamEventDto;
import ru.practicum.ewm.events.dto.UpdateEventAdminRequest;
import ru.practicum.ewm.events.service.AdminEventService;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@RestController
@Validated
@RequestMapping("/admin/events")
public class AdminEventsController {
    private final AdminEventService service;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Collection<EventFullDto> getEvents(@RequestParam(required = false, defaultValue = "") List<Long> users,
                                              @RequestParam(required = false, defaultValue = "") List<String> states,
                                              @RequestParam(required = false, defaultValue = "") List<Long> categories,
                                              @RequestParam(required = false) LocalDateTime rangeStart,
                                              @RequestParam(required = false) LocalDateTime rangeEnd,
                                              @RequestParam(defaultValue = "0") @PositiveOrZero Integer from,
                                              @RequestParam(defaultValue = "10") @Positive Integer size) {
        List<States> listStates = List.of();
        if (states != null) {
            listStates = states.stream().map(States::from)
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .toList();
        }

        NewParamEventDto params = new NewParamEventDto(users, listStates, categories, rangeStart, rangeEnd, from, size);
        log.info("Получен запрос GET /admin/events со списком параметров {}", params);
        return service.getEvents(params);
    }

    @PatchMapping("/{event-id}")
    @ResponseStatus(HttpStatus.OK)
    public EventFullDto update(@PathVariable("event-id") @Positive Long eventId,
                               @RequestBody @Valid UpdateEventAdminRequest request) {
        log.info("Получен запрос PATCH /admin/events/{}", eventId);
        return service.update(request, eventId);
    }
}
