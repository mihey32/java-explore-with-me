package ru.practicum.ewm.comments;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByAuthorId(Long userId, PageRequest pageRequest);

    List<Comment> findAllByEventIdIn(List<Long> eventIds);

    List<Comment> findAllByAuthorIdAndEventId(Long userId, Long eventId, PageRequest pageRequest);

    Comment findByIdAndAuthorIdAndEventId(Long comId, Long userId, Long eventId);

    List<Comment> findAllByEventId(Long eventId, PageRequest pageRequest);

    List<Comment> findAllByEventId(Long eventId);
}
