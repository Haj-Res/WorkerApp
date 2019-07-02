package com.hajres.domain.dao;

import com.hajres.domain.entity.Role;

import java.util.List;

public interface RoleDAO {

    Role findRoleByName(String name);

    List<Role> findAllRoles();
}
