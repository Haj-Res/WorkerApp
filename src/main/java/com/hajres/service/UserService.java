package com.hajres.service;

import com.hajres.PaginatedResult;
import com.hajres.domain.dto.EditUserDto;
import com.hajres.domain.dto.PasswordDto;
import com.hajres.domain.dto.RegHelperUser;
import com.hajres.domain.dto.UserDisplayDTO;
import com.hajres.domain.entity.Role;
import com.hajres.domain.entity.news.Country;
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

    PaginatedResult<UserDisplayDTO> findAllPaginatedUser(int pageSize, int page);

    List<Role> findAllRoles();

    EditUserDto findEditUserDTOByUsername(String username);
}
