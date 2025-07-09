package ru.practicum.ewm.events.dto;

import lombok.*;
import ru.practicum.ewm.enums.AdminStateAction;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UpdateEventAdminRequest extends UpdateEventRequest {
    private AdminStateAction stateAction;

    public boolean hasStateAction() {
        return this.stateAction != null;
    }
}