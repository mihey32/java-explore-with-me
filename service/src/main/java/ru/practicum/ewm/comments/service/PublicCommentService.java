package ru.practicum.ewm.comments.service;

import ru.practicum.ewm.comments.dto.CommentFullDto;
import ru.practicum.ewm.comments.dto.CommentShortDto;

import java.util.Collection;

public interface PublicCommentService {
    Collection<CommentShortDto> getAll(Long eventId, Integer from, Integer size);

    CommentFullDto get(Long comId);
}
