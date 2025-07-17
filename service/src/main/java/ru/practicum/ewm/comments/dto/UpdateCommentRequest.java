package ru.practicum.ewm.comments.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCommentRequest {
    @NotBlank(message = "Комментарий не может быть пустым")
    private String text;

    public boolean hasText() {
        return this.text != null;
    }
}
