package ru.practicum.server.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.dto.EndpointHitDto;
import ru.practicum.dto.ViewStatsDto;
import ru.practicum.server.mapper.EndpointHitMapper;
import ru.practicum.server.mapper.ViewStatsMapper;
import ru.practicum.server.repository.StatsRepository;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class StatServiceImpl implements StatService {
    private final StatsRepository statsRepository;

    @Autowired
    public StatServiceImpl(StatsRepository statsRepository) {
        this.statsRepository = statsRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ViewStatsDto> get(LocalDateTime start, LocalDateTime end, List<String> uris, Boolean unique) {
        PageRequest pageable = PageRequest.of(0, 10);

        if (unique.equals(Boolean.TRUE)) {
            log.info("Получаем статистику обращений с {} по {}", start, end);
            return ViewStatsMapper.mapToListDto(statsRepository.getUniqueHits(start, end, uris, pageable));
        } else {
            log.info("Получаем статистику уникальных обращений с {} по {}", start, end);
            return ViewStatsMapper.mapToListDto(statsRepository.getHits(start, end, uris, pageable));
        }
    }

    @Override
    @Transactional
    public EndpointHitDto save(EndpointHitDto requestDto) {
        log.info("Сохраняем в статистику обращение из {} к {} с ip {}", requestDto.getApp(), requestDto.getUri(), requestDto.getId());
        return EndpointHitMapper.mapToDto(statsRepository.save(EndpointHitMapper.mapToEntity(requestDto)));
    }
}
