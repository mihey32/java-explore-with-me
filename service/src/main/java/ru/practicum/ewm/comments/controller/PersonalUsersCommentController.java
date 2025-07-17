package ru.practicum.ewm.comments.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.comments.dto.CommentFullDto;
import ru.practicum.ewm.comments.dto.NewCommentDto;
import ru.practicum.ewm.comments.dto.UpdateCommentRequest;
import ru.practicum.ewm.comments.service.PersonalUserCommentService;

import java.util.Collection;

@Slf4j
@RequiredArgsConstructor
@RestController
@Validated
@RequestMapping("/users/{user-id}/comments")
public class PersonalUsersCommentController {

    private final PersonalUserCommentService service;

    @PostMapping("/{event-id}")
    @ResponseStatus(HttpStatus.CREATED)
    public CommentFullDto save(@PathVariable("user-id") @Positive Long userId,
                               @PathVariable("event-id") @Positive Long eventId,
                               @RequestBody @Valid NewCommentDto request) {
        log.info("Получен запрос POST /users/{}/events c событием {}", userId, request);
        return service.save(userId, eventId, request);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Collection<CommentFullDto> getUserComments(@PathVariable("user-id") @Positive Long userId,
                                                      @RequestParam(defaultValue = "0") @PositiveOrZero Integer from,
                                                      @RequestParam(defaultValue = "10") @Positive Integer size) {
        log.info("Получен запрос GET /users/{}/comments с параметрами from = {}, size = {}", userId, from, size);
        return service.getAll(userId, from, size);
    }

    @GetMapping("/{event-id}")
    @ResponseStatus(HttpStatus.OK)
    public Collection<CommentFullDto> getUserEventComments(@PathVariable("user-id") @Positive Long userId,
                                                           @PathVariable("event-id") @Positive Long eventId,
                                                           @RequestParam(defaultValue = "0") @PositiveOrZero Integer from,
                                                           @RequestParam(defaultValue = "10") @Positive Integer size) {
        log.info("Получен запрос GET /users/{}/comments/{} с параметрами from = {}, size = {}", userId, eventId, from, size);
        return service.getAllByEvent(userId, eventId, from, size);
    }

    @GetMapping("/{event-id}/{com-id}")
    @ResponseStatus(HttpStatus.OK)
    public CommentFullDto getUserEventComments(@PathVariable("user-id") @Positive Long userId,
                                               @PathVariable("event-id") @Positive Long eventId,
                                               @PathVariable("com-id") @Positive Long comId) {
        log.info("Получен запрос GET /users/{}/comments/{}/{}", userId, eventId, comId);
        return service.getCommentByEvent(userId, eventId, comId);
    }

    @PatchMapping("/{com-id}")
    @ResponseStatus(HttpStatus.OK)
    public CommentFullDto update(@PathVariable("user-id") @Positive Long userId,
                                 @PathVariable("com-id") @Positive Long comId,
                                 @RequestBody @Valid UpdateCommentRequest request) {
        log.info("Получен запрос PATCH /users/{}/comments/{} на обновление комментария", userId, comId);
        return service.update(userId, comId, request);
    }

    @DeleteMapping("/{com-id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("user-id") @Positive Long userId,
                       @PathVariable("com-id") @Positive Long comId) {
        log.info("Получен запрос DELETE /users/{}/comments/{}", userId, comId);
        service.delete(userId, comId);
    }
}

