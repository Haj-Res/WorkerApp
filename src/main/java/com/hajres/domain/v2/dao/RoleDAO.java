package com.hajres.domain.v2.dao;

import com.hajres.domain.model.Role;

public interface RoleDAO {

    Role findRoleByName(String name);
}
