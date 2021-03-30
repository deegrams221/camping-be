package com.dianagrams.services;

import java.util.List;

import com.dianagrams.models.Role;

public interface RoleService {
    List<Role> findAll();

    Role findRoleById(long id);

    void delete(long id);

    Role save(Role role);

    Role findByName(String name);

    Role update(long id, Role role);
}
