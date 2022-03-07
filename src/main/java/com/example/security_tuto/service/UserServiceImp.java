package com.example.security_tuto.service;

import com.example.security_tuto.model.AppUser;
import com.example.security_tuto.model.Role;
import com.example.security_tuto.repository.RoleRepository;
import com.example.security_tuto.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service @RequiredArgsConstructor @Transactional @Slf4j
public class UserServiceImp implements UserService, UserDetailsService {

    private final UserRepository Ur;
    private final RoleRepository Rr;
    private final PasswordEncoder Pe;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user = Ur.findByUsername(username);
        if (user==null){
            log.error("User Not found");
            throw new UsernameNotFoundException("User Not found");
        }else {
            log.info("User {} found !",username);
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> {authorities.add(new SimpleGrantedAuthority(role.getName()));});
        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),authorities);
    }

    @Override
    public AppUser SaveUser(AppUser user) {
        log.info("Adding this user {} to the database",user);
        user.setPassword(Pe.encode(user.getPassword()));
        return Ur.save(user);
    }

    @Override
    public Role SaveRole(Role role) {
        return Rr.save(role);
    }

    @Override
    public void AddRoleToUser(String username, String roleName) {
        AppUser user = Ur.findByUsername(username);
        Role role = Rr.findByName(roleName);
        user.getRoles().add(role);

    }

    @Override
    public AppUser getUser(String username) {

        return Ur.findByUsername(username);

    }

    @Override
    public List<AppUser> getUsers() {


        return Ur.findAll();

    }
}
