package ru.practicum.ewm.events.service;

import jakarta.servlet.http.HttpServletRequest;
import ru.practicum.ewm.events.dto.EventRequestParam;
import ru.practicum.ewm.events.dto.EventWithCommentsDto;

import java.util.Collection;

public interface PublicEventsService {
    Collection<EventWithCommentsDto> getAll(EventRequestParam param);

    EventWithCommentsDto get(Long id, HttpServletRequest request);
}
