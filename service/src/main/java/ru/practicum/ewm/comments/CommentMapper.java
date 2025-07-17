package ru.practicum.ewm.comments;

import lombok.experimental.UtilityClass;
import ru.practicum.ewm.comments.dto.CommentFullDto;
import ru.practicum.ewm.comments.dto.CommentShortDto;
import ru.practicum.ewm.comments.dto.NewCommentDto;
import ru.practicum.ewm.comments.dto.UpdateCommentRequest;
import ru.practicum.ewm.events.Event;
import ru.practicum.ewm.users.User;
import ru.practicum.ewm.users.UserMapper;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class CommentMapper {

    public static Comment mapToEntity(NewCommentDto request, User author, Event event) {
        Comment comment = new Comment();
        comment.setText(request.getText());
        comment.setEvent(event);
        comment.setAuthor(author);
        comment.setCreated(LocalDateTime.now());
        comment.setEdited(LocalDateTime.now());

        return comment;
    }

    public static CommentFullDto mapToDto(Comment comment) {
        CommentFullDto dto = new CommentFullDto();
        dto.setId(comment.getId());
        dto.setText(comment.getText());
        dto.setEvent(comment.getEvent().getId());
        dto.setAuthor(UserMapper.mapToDto(comment.getAuthor()));
        dto.setCreated(comment.getCreated());
        dto.setEdited(comment.getCreated());

        return dto;
    }

    public static CommentShortDto mapToShortDto(Comment comment) {
        CommentShortDto dto = new CommentShortDto();
        dto.setId(comment.getId());
        dto.setText(comment.getText());
        dto.setAuthor(UserMapper.mapToShortDto(comment.getAuthor()));
        dto.setEdited(comment.getCreated());

        return dto;
    }

    public static Comment updateFields(Comment entity, UpdateCommentRequest request) {
        if (request.hasText()) {
            entity.setText(request.getText());
        }

        entity.setEdited(LocalDateTime.now());

        return entity;
    }

    public static List<CommentFullDto> mapToListDto(List<Comment> listEntity) {
        return listEntity.stream().map(CommentMapper::mapToDto).collect(Collectors.toList());
    }

    public static List<CommentShortDto> mapToListShortDto(List<Comment> listEntity) {
        return listEntity.stream().map(CommentMapper::mapToShortDto).collect(Collectors.toList());
    }
}
