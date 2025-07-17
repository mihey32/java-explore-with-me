package ru.practicum.ewm.comments.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.practicum.ewm.comments.Comment;
import ru.practicum.ewm.comments.CommentMapper;
import ru.practicum.ewm.comments.CommentRepository;
import ru.practicum.ewm.comments.dto.CommentFullDto;
import ru.practicum.ewm.comments.dto.CommentShortDto;
import ru.practicum.ewm.enums.States;
import ru.practicum.ewm.events.Event;
import ru.practicum.ewm.events.EventRepository;
import ru.practicum.ewm.exceptions.ConflictException;
import ru.practicum.ewm.exceptions.NotFoundException;

import java.util.Collection;
import java.util.List;

@Slf4j
@Service
public class PublicCommentServiceImpl implements PublicCommentService {
    private final CommentRepository commentRepository;

    private final EventRepository eventRepository;

    @Autowired
    public PublicCommentServiceImpl(CommentRepository commentRepository, EventRepository eventRepository) {
        this.commentRepository = commentRepository;
        this.eventRepository = eventRepository;
    }

    private Comment findById(Long comId) {
        return commentRepository.findById(comId)
                .orElseThrow(() -> new NotFoundException(String.format("Комментарий c ID %d не найден", comId)));
    }

    private Event findEventById(Long eventId) {
        return eventRepository.findById(eventId)
                .orElseThrow(() -> new NotFoundException(String.format("Событие с ID %dне найдено", eventId)));
    }

    @Override
    public Collection<CommentShortDto> getAll(Long eventId, Integer from, Integer size) {
        PageRequest pageRequest = PageRequest.of(from / size, size, Sort.by(Sort.Direction.ASC, "created"));
        Event event = findEventById(eventId);

        if (!event.getState().equals(States.PUBLISHED)) {
            throw new ConflictException("Комментарий может быть добавлен только к опубликованному событию!");
        }

        List<Comment> comments = commentRepository.findAllByEventId(eventId, pageRequest);

        log.info("Получаем {} коммент. о событии для посетителей сервиса", comments.size());
        return CommentMapper.mapToListShortDto(comments);
    }

    @Override
    public CommentFullDto get(Long comId) {
        return CommentMapper.mapToDto(findById(comId));
    }
}
