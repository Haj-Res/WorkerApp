package com.hajres.service;

import com.hajres.domain.dao.CountryDAO;
import com.hajres.domain.dto.EditUserDto;
import com.hajres.domain.dto.PasswordDto;
import com.hajres.domain.entity.Country;
import com.hajres.domain.entity.Role;
import com.hajres.domain.entity.User;
import com.hajres.domain.dao.RoleDAO;
import com.hajres.domain.dao.UserDAO;
import com.hajres.domain.dto.RegHelperUser;
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
    private CountryDAO countryDAO;
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

        user.addRole(roleDAO.findRoleByName("ROLE_EMPLOYEE"));

        if (regHelperUser.getRoles() != null) {
            regHelperUser.getRoles().forEach(role -> user.addRole(roleDAO.findRoleByName(role)));
        }
        logger.info("Saving user: " + user.toString());

        userDAO.save(user);
    }

    @Override
    @Transactional
    public void update(EditUserDto userDto, User user) {
        Country country = countryDAO.findById((userDto.getCountry()));

        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setCountryPreference(country);
        String category = userDto.getCategory().isEmpty() ? null : userDto.getCategory();
        user.setCategoryPreference(category);

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
    public List<Country> findAllCountries() {
        return countryDAO.findAll();
    }

    @Override
    @Transactional
    public void updatePreferences(User user, String countryCode, String category) {
        Country country = countryDAO.findById(countryCode);
        user.setCountryPreference(country);
        if (category == null || category.isEmpty()) {
            category = null;
        }
        user.setCategoryPreference(category);
        userDAO.save(user);
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
