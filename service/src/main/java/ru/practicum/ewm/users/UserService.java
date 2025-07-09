package ru.practicum.ewm.users;

import ru.practicum.ewm.users.dto.NewUserRequest;
import ru.practicum.ewm.users.dto.UserDto;

import java.util.Collection;
import java.util.List;

public interface UserService {
    UserDto save(NewUserRequest request);

    Collection<UserDto> getUsers(List<Long> ids, Integer from, Integer size);

    void delete(Long userId);
}
