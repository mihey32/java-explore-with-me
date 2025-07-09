package ru.practicum.ewm.events.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class EventRequestStatusUpdateRequest {
    @NotNull(message = "При обновлении, ID запросов должны существовать")
    private List<Long> requestIds;

    @NotBlank(message = "Статус обновляемых запросов должен существовать и быть не пустым")
    private String status;
}