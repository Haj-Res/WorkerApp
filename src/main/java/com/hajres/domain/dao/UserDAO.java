package com.hajres.domain.dao;

import com.hajres.domain.model.User;

public interface UserDAO {
    User findByUserName(String name);

    void save(User user);
}
