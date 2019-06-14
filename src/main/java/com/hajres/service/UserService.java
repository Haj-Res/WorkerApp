package com.hajres.service;

import com.hajres.domain.model.RegHelperUser;
import com.hajres.domain.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User findByUserName(String name);

    void save(RegHelperUser user);
}
