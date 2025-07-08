package ru.practicum.ewm.events.service;

import ru.practicum.ewm.events.dto.*;
import ru.practicum.ewm.requests.dto.ParticipationRequestDto;

import java.util.Collection;

public interface PersonalUserEventService {
    EventFullDto save(Long userId, NewEventDto request);

    Collection<EventShortDto> getAll(Long userId, Integer from, Integer size);

    EventFullDto get(Long userId, Long eventId);

    Collection<ParticipationRequestDto> getRequests(Long userId, Long eventId);

    EventFullDto update(Long userId, Long eventId, UpdateEventUserRequest request);

    EventRequestStatusUpdateResult updateRequestsStatus(Long userId, Long eventId, EventRequestStatusUpdateRequest request);
}
