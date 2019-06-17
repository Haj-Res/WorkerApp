package com.hajres.service;

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
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private RoleDAO roleDAO;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    private Logger logger = LogManager.getLogger(getClass().getName());

    @Override
    @Transactional
    public User findByUserName(String name) {
        logger.info("Finding user \"" + name +"\".");
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

        regHelperUser.getRoles().forEach(role -> user.addRole(roleDAO.findRoleByName(role)));
        logger.info("Saving user: " + user.toString());

        userDAO.save(user);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("Finding user \"" + username +"\".");
        User user = userDAO.findByUserName(username);
        if (user == null) {
            logger.warn("Failed loggin attempt.");
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}
