package ru.practicum.ewm.categories.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.ewm.categories.Category;
import ru.practicum.ewm.categories.CategoryMapper;
import ru.practicum.ewm.categories.CategoryRepository;
import ru.practicum.ewm.categories.dto.CategoryDto;
import ru.practicum.ewm.exceptions.NotFoundException;

import java.util.Collection;
import java.util.List;

@Slf4j
@Service
public class CategoryServicePublicImpl implements CategoryServicePublic {
    private final CategoryRepository categoryRepository;

    public CategoryServicePublicImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category findById(Long catId) {
        return categoryRepository.findById(catId)
                .orElseThrow(() -> new NotFoundException(String.format("Категория c ID %d не найдена", catId)));
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<CategoryDto> getAll(Integer from, Integer size) {
        PageRequest pageRequest = PageRequest.of(from / size, size, Sort.by(Sort.Direction.DESC, "id"));

        List<Category> test = categoryRepository.findAll(pageRequest).toList();

        return CategoryMapper.mapToListDto(test);
    }

    @Override
    @Transactional(readOnly = true)
    public CategoryDto get(Long catId) {
        return CategoryMapper.mapToDto(findById(catId));
    }
}
