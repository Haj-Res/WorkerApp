package com.hajres.service;

import com.hajres.PaginatedResult;
import com.hajres.domain.dao.NewsDAO;
import com.hajres.domain.dto.EditUserDto;
import com.hajres.domain.dto.PasswordDto;
import com.hajres.domain.dto.UserDisplayDTO;
import com.hajres.domain.entity.news.Country;
import com.hajres.domain.entity.Role;
import com.hajres.domain.entity.User;
import com.hajres.domain.dao.RoleDAO;
import com.hajres.domain.dao.UserDAO;
import com.hajres.domain.dto.RegHelperUser;
import com.hajres.domain.entity.news.NewsCategory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;
    @Autowired
    private RoleDAO roleDAO;
    @Autowired
    private NewsDAO newsDAO;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    private Logger logger = LogManager.getLogger(getClass().getName());

    @Override
    @Transactional
    public User findByUserName(String name) {
        logger.info("Finding User by username \"" + name + "\".");
        return userDAO.findByUserName(name);
    }

    @Override
    @Transactional
    public void save(RegHelperUser regHelperUser) {
        User user = new User();

        user.setUsername(regHelperUser.getUsername());
        user.setPassword(passwordEncoder.encode(regHelperUser.getPassword()));
        user.setFirstName(regHelperUser.getFirstName());
        user.setLastName(regHelperUser.getLastName());
        user.setEmail(regHelperUser.getEmail());

        Country country = newsDAO.findCountryById(regHelperUser.getCountry());
        user.setCountryPreference(country);

        NewsCategory category = newsDAO.findCategoryById(regHelperUser.getCategory());
        user.setCategoryPreference(category);

        user.addRole(roleDAO.findRoleByName("ROLE_USER"));

        if (regHelperUser.getRoles() != null) {
            regHelperUser.getRoles().forEach(role -> user.addRole(roleDAO.findRoleByName(role)));
        }
        logger.info("Saving user: " + user.toString());

        userDAO.save(user);
    }

    @Override
    @Transactional
    public void update(EditUserDto userDto, User user) {
        Country country = newsDAO.findCountryById((userDto.getCountry()));
        NewsCategory category = newsDAO.findCategoryById(userDto.getCategory());

        user.setUsername(userDto.getUsername());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setCountryPreference(country);
        user.setCategoryPreference(category);
        if (userDto.getRoles() != null) {
            user.setRoles(new ArrayList<>());
            userDto.getRoles().forEach(r -> {
                Role role = roleDAO.findRoleByName(r);
                user.getRoles().add(role);
            });
        }
        userDAO.save(user);
    }

    @Override
    @Transactional
    public User updatePassword(PasswordDto passwordDto, User user) {
        if (passwordEncoder.matches(passwordDto.getOldPassword(), user.getPassword())) {
            user.setPassword(passwordEncoder.encode(passwordDto.getNewPassword()));
            userDAO.save(user);
            return user;
        } else {
            return null;
        }
    }

    @Override
    @Transactional
    public User updatePasswordAdmin(PasswordDto passwordDto, User user) {
        user.setPassword(passwordEncoder.encode(passwordDto.getNewPassword()));
        userDAO.save(user);
        return user;
    }

    @Override
    @Transactional
    public List<Country> findAllCountries() {
        return newsDAO.findAllCountries();
    }

    @Override
    @Transactional
    public void updatePreferences(User user, String countryCode, String category) {
        Country country = newsDAO.findCountryById(countryCode);
        NewsCategory newsCategory = newsDAO.findCategoryById(category);
        user.setCountryPreference(country);
        user.setCategoryPreference(newsCategory);
        userDAO.save(user);
    }

    @Override
    @Transactional
    public PaginatedResult<UserDisplayDTO> findAllPaginatedUser(int pageSize, int page) {
        return userDAO.findAllPaginatedUser(pageSize, page);
    }

    @Override
    @Transactional
    public List<Role> findAllRoles() {
        return roleDAO.findAllRoles();
    }

    @Override
    @Transactional
    public EditUserDto findEditUserDTOByUsername(String username) {
        User user = findByUserName(username);
        if (user == null) {
            return null;
        }
        return EditUserDto.map(user);
    }

    @Override
    @Transactional
    public void deleteUser(String username) {
        userDAO.delete(username);
    }


    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("Loading UserDetails by username \"" + username + "\".");
        User user = userDAO.findByUserName(username);
        if (user == null) {
            logger.warn("Failed log in attempt for username \"" + username + "\".");
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}
