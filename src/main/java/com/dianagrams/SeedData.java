package com.dianagrams;

import java.util.ArrayList;

import com.dianagrams.models.Role;
import com.dianagrams.models.User;
import com.dianagrams.models.UserRoles;
import com.dianagrams.models.Useremail;
import com.dianagrams.repositories.RoleRepository;
import com.dianagrams.repositories.UserRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Component
public class SeedData implements CommandLineRunner {
    RoleRepository rolerepos;
    UserRepository userrepos;

    public SeedData(RoleRepository rolerepos, UserRepository userrepos) {
        this.rolerepos = rolerepos;
        this.userrepos = userrepos;
    }

    @Override
    public void run(String[] args) throws Exception {
        Role r1 = new Role("ADMIN");

        rolerepos.save(r1);

        ArrayList<UserRoles> admins = new ArrayList<>();
        admins.add(new UserRoles(new User(), r1));
        User u1 = new User("admin", "password", "admin@home.local", admins);
        u1.getUseremails().add(new Useremail(u1, "admin@email.local"));
        u1.getUseremails().add(new Useremail(u1, "admin@mymail.local"));
        userrepos.save(u1);
    }
}