package ru.practicum.ewm.users;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.ewm.exceptions.ConflictException;
import ru.practicum.ewm.exceptions.NotFoundException;
import ru.practicum.ewm.users.dto.NewUserRequest;
import ru.practicum.ewm.users.dto.UserDto;

import java.util.Collection;
import java.util.List;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private User findById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(String.format("Пользователь c ID %d не найден", userId)));
    }

    @Override
    @Transactional
    public UserDto save(NewUserRequest request) {
        User user = UserMapper.mapToEntity(request);
        try {
            user = userRepository.save(user);
        } catch (DataIntegrityViolationException e) {
            throw new ConflictException(String.format("Email %s уже занят", user.getEmail()), e);
        }
        log.info("Сохраняем данные о пользователе {}", request.getName());
        return UserMapper.mapToDto(user);
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<UserDto> getUsers(List<Long> ids, Integer from, Integer size) {
        List<User> users;

        PageRequest pageRequest = PageRequest.of(from / size, size, Sort.by(Sort.Direction.ASC, "id"));
        if (ids == null) {
            users = userRepository.findAll(pageRequest).toList();
        } else {
            users = userRepository.findAllByIdIn(ids, pageRequest);
        }

        log.info("Получаем данные о {} пользователях", users.size());
        return UserMapper.mapToListDto(users);
    }

    @Override
    @Transactional
    public void delete(Long userId) {
        User user = findById(userId);
        userRepository.deleteById(userId);
        log.info("Пользователь {} удален", user.getName());
    }
}
