package ru.practicum.server.mapper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DateTimeMapper {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static LocalDateTime toLocalDateTime(String date) {
        return LocalDateTime.parse(date, FORMATTER);
    }

    public static String toStringDate(LocalDateTime date) {
        return date.format(FORMATTER);
    }
}
