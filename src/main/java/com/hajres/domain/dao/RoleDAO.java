package com.hajres.domain.dao;

import com.hajres.domain.entity.Role;

public interface RoleDAO {

    Role findRoleByName(String name);
}
