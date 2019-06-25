package com.hajres.domain.dao;

import com.hajres.domain.dto.EditUserDto;
import com.hajres.domain.entity.Country;
import com.hajres.domain.entity.User;

public interface UserDAO {
    User findByUserName(String name);

    void save(User user);

    void update(EditUserDto userDto, User user, Country country);
}
