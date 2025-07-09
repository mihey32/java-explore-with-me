package ru.practicum.ewm.categories.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@ToString
@EqualsAndHashCode(of = {"name"})
public class NewCategoryDto {
    @NotBlank(message = "Название добавляемой категории не может быть пустым!")
    @Length(min = 1, max = 50)
    private String name;
}
