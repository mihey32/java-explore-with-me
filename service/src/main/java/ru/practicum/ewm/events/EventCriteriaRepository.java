package ru.practicum.ewm.events;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import ru.practicum.ewm.events.dto.NewParamEventDto;

@Repository
public interface EventCriteriaRepository {
    Page<Event> findAllWithCriteria(PageRequest pageRequest, EventCriteria eventSearchCriteria);

    Page<Event> findAllWithCriteria(PageRequest pageRequest, NewParamEventDto newParamEventDto);

}
