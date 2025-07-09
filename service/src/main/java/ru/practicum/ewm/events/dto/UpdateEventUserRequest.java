package ru.practicum.ewm.events.dto;

import lombok.*;
import ru.practicum.ewm.enums.UserStateAction;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UpdateEventUserRequest extends UpdateEventRequest {
    private UserStateAction stateAction;

    public boolean hasStateAction() {
        return this.stateAction != null;
    }
}
