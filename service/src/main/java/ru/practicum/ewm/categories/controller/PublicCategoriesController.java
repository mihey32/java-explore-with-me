package ru.practicum.ewm.categories.controller;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.categories.dto.CategoryDto;
import ru.practicum.ewm.categories.service.CategoryServicePublic;

import java.util.Collection;

@Slf4j
@RequiredArgsConstructor
@RestController
@Validated
@RequestMapping("/categories")
public class PublicCategoriesController {
    private final CategoryServicePublic service;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Collection<CategoryDto> getAll(@RequestParam(defaultValue = "0") @PositiveOrZero Integer from,
                                          @RequestParam(defaultValue = "10") @Positive Integer size) {
        log.info("Получен запрос GET /categories с параметрами from = {}, size = {}", from, size);
        return service.getAll(from, size);
    }

    @GetMapping("/{cat-id}")
    @ResponseStatus(HttpStatus.OK)
    public CategoryDto get(@PathVariable("cat-id") Long catId) {
        log.info("Получен запрос GET /categories/{}", catId);
        return service.get(catId);
    }
}
