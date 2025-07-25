package ru.practicum.ewm.requests;

import lombok.experimental.UtilityClass;
import ru.practicum.ewm.enums.Statuses;
import ru.practicum.ewm.events.Event;
import ru.practicum.ewm.requests.dto.ParticipationRequestDto;
import ru.practicum.ewm.users.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class RequestMapper {
    public static Request mapToEntity(Event event, User requester) {
        Request request = new Request();

        request.setEvent(event);
        request.setRequester(requester);
        request.setCreated(LocalDateTime.now());
        request.setStatus(!event.getRequestModeration() || event.getParticipantLimit().equals(0L) ? Statuses.CONFIRMED : Statuses.PENDING);

        return request;
    }

    public static ParticipationRequestDto mapToDto(Request request) {
        ParticipationRequestDto dto = new ParticipationRequestDto();

        dto.setId(request.getId());
        dto.setEvent(request.getEvent().getId());
        dto.setRequester(request.getRequester().getId());
        dto.setCreated(request.getCreated());
        dto.setStatus(request.getStatus());

        return dto;
    }

    public static List<ParticipationRequestDto> mapToListDto(List<Request> listEntity) {
        return listEntity.stream().map(RequestMapper::mapToDto).collect(Collectors.toList());
    }
}
