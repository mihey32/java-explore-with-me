package ru.practicum.ewm.requests.service;

import ru.practicum.ewm.requests.dto.ParticipationRequestDto;

import java.util.Collection;

public interface PersonalUserRequestService {
    ParticipationRequestDto save(Long userId, Long eventId);

    Collection<ParticipationRequestDto> get(Long userId);

    ParticipationRequestDto update(Long userId, Long requestId);
}
