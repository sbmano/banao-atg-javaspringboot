package com.Banao.Authentication.controller;

import com.Banao.Authentication.model.Role;
import com.Banao.Authentication.model.Users;
import com.Banao.Authentication.service.RoleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping("/api")
public class RoleController {

    @Autowired
    private RoleServiceImpl roleServiceimpl;

    @PostMapping("/addrole")
    private ResponseEntity<Role> addrole(@RequestBody Role role){
        System.out.println("role");
        return ResponseEntity.ok().body(this.roleServiceimpl.addrole(role));
    }
}
