package com.hajres.domain.dao;

import com.hajres.PaginatedResult;
import com.hajres.domain.dto.UserDisplayDTO;
import com.hajres.domain.entity.User;

public interface UserDAO {
    User findByUserName(String name);

    void save(User user);

    PaginatedResult<UserDisplayDTO> findAllPaginatedUser(int pageSize, int page);
}
