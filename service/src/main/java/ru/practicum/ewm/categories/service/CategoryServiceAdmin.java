package ru.practicum.ewm.categories.service;

import ru.practicum.ewm.categories.Category;
import ru.practicum.ewm.categories.dto.CategoryDto;
import ru.practicum.ewm.categories.dto.NewCategoryDto;

public interface CategoryServiceAdmin {
    Category findById(Long catId);

    CategoryDto save(NewCategoryDto request);

    CategoryDto update(NewCategoryDto request, Long catId);

    void delete(Long catId);
}
