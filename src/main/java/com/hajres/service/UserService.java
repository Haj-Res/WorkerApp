package com.hajres.service;

import com.hajres.domain.dto.UserDTO;
import com.hajres.domain.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User findByUserName(String name);

    void save(UserDTO user);
}
