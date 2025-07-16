package ru.practicum.ewm.comments.controller;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.comments.dto.CommentFullDto;
import ru.practicum.ewm.comments.dto.CommentShortDto;
import ru.practicum.ewm.comments.service.PublicCommentService;

import java.util.Collection;

@Slf4j
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/comments")
public class PublicCommentsController {
    private final PublicCommentService service;

    @GetMapping("/event/{event-id}")
    @ResponseStatus(HttpStatus.OK)
    public Collection<CommentShortDto> getAll(@PathVariable("event-id") @Positive Long eventId,
                                              @RequestParam(defaultValue = "0") @PositiveOrZero Integer from,
                                              @RequestParam(defaultValue = "10") @Positive Integer size) {
        log.info("Получен запрос GET /comments/event/{} с параметрами from = {}, size = {}", eventId, from, size);
        return service.getAll(eventId, from, size);
    }

    @GetMapping("/{com-id}")
    @ResponseStatus(HttpStatus.OK)
    public CommentFullDto get(@PathVariable("com-id") @Positive Long comId) {
        log.info("Получен запрос GET /comments/{}", comId);
        return service.get(comId);
    }
}
