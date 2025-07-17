package ru.practicum.ewm.comments.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import ru.practicum.ewm.users.dto.UserShortDto;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@EqualsAndHashCode(of = {"id"})
@AllArgsConstructor
@NoArgsConstructor
public class CommentShortDto {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    private String text;

    private UserShortDto author;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime edited;
}
