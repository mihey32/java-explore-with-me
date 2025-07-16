package ru.practicum.ewm.comments.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.comments.dto.CommentFullDto;
import ru.practicum.ewm.comments.dto.UpdateCommentRequest;
import ru.practicum.ewm.comments.service.AdminCommentService;

@Slf4j
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/admin/comments")
public class AdminCommentsController {
    private final AdminCommentService service;

    @PatchMapping("/{com-id}")
    @ResponseStatus(HttpStatus.OK)
    public CommentFullDto update(@PathVariable("com-id") @Positive Long comId,
                                 @RequestBody @Valid UpdateCommentRequest request) {
        log.info("Получен запрос PATCH /admin/comments/{}", comId);
        return service.update(request, comId);
    }

    @DeleteMapping("/{com-id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("com-id") @Positive Long comId) {
        log.info("Получен запрос DELETE /admin/comments/{}", comId);
        service.delete(comId);
    }
}
