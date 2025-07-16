package ru.practicum.ewm.comments.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.ewm.comments.Comment;
import ru.practicum.ewm.comments.CommentMapper;
import ru.practicum.ewm.comments.CommentRepository;
import ru.practicum.ewm.comments.dto.CommentFullDto;
import ru.practicum.ewm.comments.dto.UpdateCommentRequest;
import ru.practicum.ewm.exceptions.ConflictException;
import ru.practicum.ewm.exceptions.NotFoundException;
import ru.practicum.ewm.users.UserRepository;

@Slf4j
@Service
public class AdminCommentServiceImpl implements AdminCommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    @Autowired
    public AdminCommentServiceImpl(CommentRepository commentRepository,
                                   UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
    }

    private Comment findById(Long comId) {
        return commentRepository.findById(comId)
                .orElseThrow(() -> new NotFoundException(String.format("Комментарий c ID %d не найден", comId)));
    }

    @Override
    @Transactional
    public CommentFullDto update(UpdateCommentRequest request, Long comId) {
        Comment updatedComment = CommentMapper.updateFields(findById(comId), request);

        try {
            updatedComment = commentRepository.save(updatedComment);
        } catch (DataIntegrityViolationException e) {
            throw new ConflictException(e.getMessage(), e);
        }

        log.info("Администратор модерирует комментарий с ID \"{}\"", updatedComment.getId());
        return CommentMapper.mapToDto(updatedComment);
    }

    @Override
    @Transactional
    public void delete(Long comId) {
        Comment comment = findById(comId);
        userRepository.deleteById(comId);
        log.info("Комментарий с ID {} удален администратором", comment.getId());
    }
}
