package com.hajres.domain.dao;

import com.hajres.domain.model.Role;

public interface RoleDAO {

    Role findRoleByName(String name);
}
