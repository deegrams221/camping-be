package com.dianagrams.controllers;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.validation.Valid;

import com.dianagrams.models.Role;
import com.dianagrams.services.RoleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/roles")
public class RolesController {
        @Autowired
        RoleService roleService;

        // GET - All roles
        // http://localhost:2019/roles/roles
        @GetMapping(value = "/roles", produces = { "application/json" })
        public ResponseEntity<?> listRoles() {
                List<Role> allRoles = roleService.findAll();
                return new ResponseEntity<>(allRoles, HttpStatus.OK);
        }

        // GET - Role by id
        // http://localhost:2019/roles/role/{roleId}
        @GetMapping(value = "/role/{roleId}", produces = { "application/json" })
        public ResponseEntity<?> getRoleById(@PathVariable Long roleId) {
                Role r = roleService.findRoleById(roleId);
                return new ResponseEntity<>(r, HttpStatus.OK);
        }

        // GET - Role by name
        // http://localhost:2019/roles/role/name/{roleName}
        @GetMapping(value = "/role/name/{roleName}", produces = { "application/json" })
        public ResponseEntity<?> getRoleByName(@PathVariable String roleName) {
                Role r = roleService.findByName(roleName);
                return new ResponseEntity<>(r, HttpStatus.OK);
        }

        // POST
        // http://localhost:2019/roles/role
        @PostMapping(value = "/role")
        public ResponseEntity<?> addNewRole(@Valid @RequestBody Role newRole) throws URISyntaxException {
                newRole = roleService.save(newRole);

                // set the location header for the newly created resource
                HttpHeaders responseHeaders = new HttpHeaders();
                URI newRoleURI = ServletUriComponentsBuilder.fromCurrentRequest().path("/{roleid}")
                                .buildAndExpand(newRole.getRoleid()).toUri();
                responseHeaders.setLocation(newRoleURI);

                return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
        }

        // PUT
        // http://localhost:2019/roles/role{roleid}
        @PutMapping(value = "/role/{roleid}")
        public ResponseEntity<?> addNewRole(@PathVariable long roleid, @Valid @RequestBody Role newRole)
                        throws URISyntaxException {
                newRole = roleService.update(roleid, newRole);
                return new ResponseEntity<>(HttpStatus.CREATED);
        }

        // DELETE
        // localhost:2019/roles/role/3
        @DeleteMapping("/role/{id}")
        public ResponseEntity<?> deleteRoleById(@PathVariable long id) {
                roleService.delete(id);
                return new ResponseEntity<>(HttpStatus.OK);
        }
}
