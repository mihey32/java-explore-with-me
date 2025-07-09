package ru.practicum.ewm.events.service;

import ru.practicum.ewm.events.dto.EventFullDto;
import ru.practicum.ewm.events.dto.NewParamEventDto;
import ru.practicum.ewm.events.dto.UpdateEventAdminRequest;

import java.util.Collection;

public interface AdminEventService {
    Collection<EventFullDto> getEvents(NewParamEventDto params);

    EventFullDto update(UpdateEventAdminRequest request, Long eventId);
}
