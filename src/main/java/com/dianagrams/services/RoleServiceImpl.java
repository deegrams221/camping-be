package com.dianagrams.services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import com.dianagrams.models.Role;
import com.dianagrams.repositories.RoleRepository;
import com.dianagrams.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(value = "roleService")
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleRepository rolerepos;

    @Autowired
    UserRepository userrepos;

    @Override
    public List<Role> findAll() {
        List<Role> list = new ArrayList<>();
        rolerepos.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public Role findRoleById(long id) {
        return rolerepos.findById(id).orElseThrow(() -> new EntityNotFoundException("Role id " + id + " not found!"));
    }

    @Override
    public Role findByName(String name) {
        Role rr = rolerepos.findByNameIgnoreCase(name);

        if (rr != null) {
            return rr;
        } else {
            throw new EntityNotFoundException(name);
        }
    }

    @Transactional
    @Override
    public void delete(long id) {
        rolerepos.findById(id).orElseThrow(() -> new EntityNotFoundException("Role id " + id + " not found!"));
        rolerepos.deleteById(id);
    }

    @Transactional
    @Override
    public Role update(long id, Role role) {
        if (role.getName() == null) {
            throw new EntityNotFoundException("No role name found to update!");
        }

        if (role.getUserRoles().size() > 0) {
            throw new EntityNotFoundException(
                    "User Roles are not updated through Role. See endpoint POST: users/user/{userid}/role/{roleid}");
        }

        rolerepos.updateRoleName(id, role.getName());
        return findRoleById(id);
    }

    @Transactional
    @Override
    public Role save(Role role) {
        Role newRole = new Role();
        newRole.setName(role.getName());
        if (role.getUserRoles().size() > 0) {
            throw new EntityNotFoundException(
                    "User Roles are not updated through Role. See endpoint POST: users/user/{userid}/role/{roleid}");
        }

        return rolerepos.save(role);
    }
}
