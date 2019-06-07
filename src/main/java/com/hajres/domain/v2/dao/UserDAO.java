package com.hajres.domain.v2.dao;

import com.hajres.domain.model.User;

public interface UserDAO {
    User findByUserName(String name);

    void save(User user);
}
