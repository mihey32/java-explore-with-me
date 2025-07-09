package ru.practicum.ewm.categories.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.ewm.categories.Category;
import ru.practicum.ewm.categories.CategoryMapper;
import ru.practicum.ewm.categories.CategoryRepository;
import ru.practicum.ewm.categories.dto.CategoryDto;
import ru.practicum.ewm.categories.dto.NewCategoryDto;
import ru.practicum.ewm.events.EventRepository;
import ru.practicum.ewm.exceptions.ConflictException;
import ru.practicum.ewm.exceptions.ConstraintViolationException;
import ru.practicum.ewm.exceptions.NotFoundException;

@Slf4j
@Service
public class CategoryServiceAdminImpl implements CategoryServiceAdmin {

    private final CategoryRepository categoryRepository;
    private final EventRepository eventRepository;

    @Autowired
    public CategoryServiceAdminImpl(CategoryRepository categoryRepository, EventRepository eventRepository) {
        this.categoryRepository = categoryRepository;
        this.eventRepository = eventRepository;
    }

    @Override
    public Category findById(Long catId) {
        return categoryRepository.findById(catId)
                .orElseThrow(() -> new NotFoundException(String.format("Категория c ID %d не найдена", catId)));
    }

    @Override
    @Transactional
    public CategoryDto save(NewCategoryDto request) {
        Category category = CategoryMapper.mapToEntity(request);
        try {
            category = categoryRepository.save(category);
        } catch (DataIntegrityViolationException e) {
            throw new ConflictException(String.format("Категория \"%s\" уже существует", category.getName()), e);
        }
        log.info("Сохраняем данные о категории {}", request.getName());
        return CategoryMapper.mapToDto(category);
    }

    @Override
    @Transactional
    public CategoryDto update(NewCategoryDto request, Long catId) {
        Category updatedCategory = CategoryMapper.updateFields(findById(catId), request);

        updatedCategory = categoryRepository.save(updatedCategory);

        log.info("Обновляем категорию \"{}\"", updatedCategory.getName());
        return CategoryMapper.mapToDto(updatedCategory);
    }

    @Override
    @Transactional
    public void delete(Long catId) {
        Category category = findById(catId);
        if (eventRepository.existsByCategory(category)) {
            throw new ConstraintViolationException(String.format("Существуют события связанные с категорией %s, " +
                    "удаление категории невозможно!", category.getName()));
        } else {
            categoryRepository.deleteById(catId);
            log.info("Категория \"{}\" удалена", category.getName());
        }
    }
}
