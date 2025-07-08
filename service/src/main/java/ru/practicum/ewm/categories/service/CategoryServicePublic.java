package ru.practicum.ewm.categories.service;

import ru.practicum.ewm.categories.dto.CategoryDto;

import java.util.Collection;

public interface CategoryServicePublic {
    Collection<CategoryDto> getAll(Integer from, Integer size);

    CategoryDto get(Long catId);
}
