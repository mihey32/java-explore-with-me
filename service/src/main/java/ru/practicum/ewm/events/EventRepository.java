package ru.practicum.ewm.events;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.ewm.categories.Category;

import java.util.Optional;
import java.util.Set;

public interface EventRepository extends JpaRepository<Event, Long>, EventCriteriaRepository {
    Boolean existsByCategory(Category category);

    Set<Event> findAllByInitiatorId(Long id, PageRequest pageRequest);

    Set<Event> findAllByIdIn(Set<Long> ids);

    Optional<Event> findByIdAndInitiatorId(Long eventId, Long userId);
}
