package ru.practicum.ewm.requests;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.requests.dto.ParticipationRequestDto;
import ru.practicum.ewm.requests.service.PersonalUserRequestService;

import java.util.Collection;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/users/{user-id}/requests")
public class PersonalUsersRequestController {

    private final PersonalUserRequestService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ParticipationRequestDto save(@PathVariable("user-id") Long userId,
                                        @RequestParam(required = false) Long eventId) {
        log.info("Получен запрос POST /users/{}/requests на участие в событии с ID {}", userId, eventId);
        return service.save(userId, eventId);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Collection<ParticipationRequestDto> get(@PathVariable("user-id") Long userId) {
        log.info("Получен запрос GET /users/{}/requests", userId);
        return service.get(userId);
    }

    @PatchMapping("/{request-id}/cancel")
    @ResponseStatus(HttpStatus.OK)
    public ParticipationRequestDto update(@PathVariable("user-id") Long userId,
                                          @PathVariable("request-id") Long requestId) {
        log.info("Получен запрос PATCH /users/{}/requests/{}/cancel на отмену заявки", userId, requestId);
        return service.update(userId, requestId);
    }
}
