package ru.practicum.server.mapper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import ru.practicum.dto.EndpointHitDto;
import ru.practicum.server.model.EndpointHit;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EndpointHitMapper {
    public static EndpointHitDto mapToDto(EndpointHit entity) {
        EndpointHitDto endpointHitDto = new EndpointHitDto();
        endpointHitDto.setId(entity.getId());
        endpointHitDto.setApp(entity.getApp());
        endpointHitDto.setUri(entity.getUri());
        endpointHitDto.setIp(entity.getIp());
        endpointHitDto.setTimestamp(entity.getTimestamp());

        return endpointHitDto;
    }

    public static EndpointHit mapToEntity(EndpointHitDto dto) {
        EndpointHit endpointHit = new EndpointHit();
        endpointHit.setApp(dto.getApp());
        endpointHit.setUri(dto.getUri());
        endpointHit.setIp(dto.getIp());
        endpointHit.setTimestamp(dto.getTimestamp());

        return endpointHit;
    }
}
