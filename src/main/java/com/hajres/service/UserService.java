package com.hajres.service;

import com.hajres.domain.model.User;
import com.hajres.user.CrmUser;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User findByUserName(String name);

    void save(CrmUser user);
}
