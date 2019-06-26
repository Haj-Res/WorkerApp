package com.hajres.service;

import com.hajres.domain.dto.EditUserDto;
import com.hajres.domain.dto.PasswordDto;
import com.hajres.domain.dto.RegHelperUser;
import com.hajres.domain.entity.Country;
import com.hajres.domain.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    User findByUserName(String name);

    void save(RegHelperUser user);

    void update(EditUserDto userDto, User user);

    User updatePassword(PasswordDto passwordDto, User user);

    List<Country> findAllCountries();

    void updatePreferences(User user, String countryCode, String category);
}
