package com.example.security_tuto.Controller;

import com.example.security_tuto.model.AppUser;
import com.example.security_tuto.model.Role;
import com.example.security_tuto.service.UserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {
    @Autowired
    private final UserService Us;

    @GetMapping("/users")
    public ResponseEntity<List<AppUser>> getUsers(){

        return ResponseEntity.ok().body(Us.getUsers());
    }
    @PostMapping("/user/Save")
    public ResponseEntity<AppUser> saveUser(@RequestBody AppUser user){

        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/user/Save").toUriString());
        return ResponseEntity.created(uri).body(Us.SaveUser(user)) ;
    }
    @PostMapping("/role/Save")
    public ResponseEntity<Role> saveUser(@RequestBody Role role){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/user/Save").toUriString());
        return ResponseEntity.created(uri).body(Us.SaveRole(role)) ;
    }
    @PostMapping("/role/addRole")
    public ResponseEntity<?>addRoleToUser(@RequestBody RoleToUserForm form){
        Us.AddRoleToUser(form.getUsername(),form.getRoleName());
        return ResponseEntity.ok().build();
    }


}
@Data
class RoleToUserForm {

    private String username;
    private String roleName;
}