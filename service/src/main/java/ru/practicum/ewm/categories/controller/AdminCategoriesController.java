package ru.practicum.ewm.categories.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.categories.dto.CategoryDto;
import ru.practicum.ewm.categories.dto.NewCategoryDto;
import ru.practicum.ewm.categories.service.CategoryServiceAdminImpl;
import ru.practicum.ewm.exceptions.DuplicatedDataException;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/admin/categories")
public class AdminCategoriesController {
    private final CategoryServiceAdminImpl service;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryDto save(@RequestBody @Valid NewCategoryDto request) {
        log.info("Получен запрос POST /admin/categories c новой категорией: \"{}\"", request.getName());
        return service.save(request);
    }

    @PatchMapping("/{cat-id}")
    @ResponseStatus(HttpStatus.OK)
    public CategoryDto update(@PathVariable("cat-id") Long categoryId,
                              @RequestBody @Valid NewCategoryDto request) {
        log.info("Получен запрос PATCH /admin/categories/{}", categoryId);

        CategoryDto result;
        try {
            result = service.update(request, categoryId);
        } catch (DataIntegrityViolationException e) {
            throw new DuplicatedDataException(e.getMessage(), e);
        }

        return result;
    }

    @DeleteMapping("/{cat-id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("cat-id") Long categoryId) {
        log.info("Получен запрос DELETE /admin/categories/{}", categoryId);
        service.delete(categoryId);
    }
}
