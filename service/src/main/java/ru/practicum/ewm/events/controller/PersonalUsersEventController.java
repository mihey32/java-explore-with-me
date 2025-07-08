package ru.practicum.ewm.events.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.events.dto.*;
import ru.practicum.ewm.events.service.PersonalUserEventService;
import ru.practicum.ewm.requests.dto.ParticipationRequestDto;

import java.util.Collection;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/users/{user-id}/events")
public class PersonalUsersEventController {

    private final PersonalUserEventService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EventFullDto save(@PathVariable("user-id") Long userId,
                             @RequestBody @Valid NewEventDto request) {
        log.info("Получен запрос POST /users/{}/events c событием {}", userId, request);
        return service.save(userId, request);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Collection<EventShortDto> getUserEvents(@PathVariable("user-id") Long userId,
                                                   @RequestParam(defaultValue = "0") @PositiveOrZero Integer from,
                                                   @RequestParam(defaultValue = "10") @Positive Integer size) {
        log.info("Получен запрос GET /users/{}/events с параметрами from = {}, size = {}", userId, from, size);
        return service.getAll(userId, from, size);
    }

    @GetMapping("/{event-id}")
    @ResponseStatus(HttpStatus.OK)
    public EventFullDto getUserEvent(@PathVariable("user-id") Long userId,
                                     @PathVariable("event-id") Long eventId) {
        log.info("Получен запрос GET /users/{}/events/{}", userId, eventId);
        return service.get(userId, eventId);
    }

    @GetMapping("/{event-id}/requests")
    @ResponseStatus(HttpStatus.OK)
    public Collection<ParticipationRequestDto> getUserEventRequests(@PathVariable("user-id") Long userId,
                                                                    @PathVariable("event-id") Long eventId) {
        log.info("Получен запрос GET /users/{}/events/{}/requests", userId, eventId);
        return service.getRequests(userId, eventId);
    }

    @PatchMapping("/{event-id}")
    @ResponseStatus(HttpStatus.OK)
    public EventFullDto update(@PathVariable("user-id") Long userId,
                               @PathVariable("event-id") Long eventId,
                               @RequestBody @Valid UpdateEventUserRequest request) {
        log.info("Получен запрос PATCH /users/{}/events/{} на обновление события", userId, eventId);
        return service.update(userId, eventId, request);
    }

    @PatchMapping("/{event-id}/requests")
    @ResponseStatus(HttpStatus.OK)
    public EventRequestStatusUpdateResult updateRequestsStatus(@PathVariable("user-id") Long userId,
                                                               @PathVariable("event-id") Long eventId,
                                                               @RequestBody EventRequestStatusUpdateRequest request) {
        log.info("Получен запрос PATCH /users/{}/events/{} на обновление заявок в статус {}", userId, eventId, request.getStatus());
        return service.updateRequestsStatus(userId, eventId, request);
    }
}
