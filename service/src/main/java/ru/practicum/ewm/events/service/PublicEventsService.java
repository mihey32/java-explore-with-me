package ru.practicum.ewm.events.service;

import jakarta.servlet.http.HttpServletRequest;
import ru.practicum.ewm.events.dto.EventFullDto;
import ru.practicum.ewm.events.dto.EventRequestParam;
import ru.practicum.ewm.events.dto.EventShortDto;

import java.util.Collection;

public interface PublicEventsService {
    Collection<EventShortDto> getAll(EventRequestParam param);

    EventFullDto get(Long id, HttpServletRequest request);
}
