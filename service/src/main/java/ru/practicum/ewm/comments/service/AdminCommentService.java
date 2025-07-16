package ru.practicum.ewm.comments.service;

import ru.practicum.ewm.comments.dto.CommentFullDto;
import ru.practicum.ewm.comments.dto.UpdateCommentRequest;

public interface AdminCommentService {

    CommentFullDto update(UpdateCommentRequest request, Long comId);

    void delete(Long comId);
}
