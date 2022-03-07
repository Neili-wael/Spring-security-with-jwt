package com.example.security_tuto.service;

import com.example.security_tuto.model.AppUser;
import com.example.security_tuto.model.Role;

import java.util.List;

public interface UserService {

    AppUser SaveUser(AppUser user);
    Role SaveRole(Role role);
    void AddRoleToUser(String username,String roleName);
    AppUser getUser(String username);
    List<AppUser> getUsers();
}
