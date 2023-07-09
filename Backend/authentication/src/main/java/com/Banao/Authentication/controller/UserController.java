package com.Banao.Authentication.controller;

import com.Banao.Authentication.exception.CustomException;
import com.Banao.Authentication.model.Role;
import com.Banao.Authentication.model.Users;
import com.Banao.Authentication.repository.UserRepository;
import com.Banao.Authentication.service.UserDetailsServ;
import com.Banao.Authentication.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserDetailsServ userDetailsServ;

    @Autowired
    private UserDetailsServiceImpl userDetailsServiceimpl;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder bcrypt;
    @PostMapping("/auth/signup")
    private ResponseEntity<Users> adduser(@RequestBody Users user){
        System.out.println("signup");
        user.setPassword(this.bcrypt.encode(user.getPassword()));
        return ResponseEntity.ok().body(this.userDetailsServ.adduser(user));
    }

    @GetMapping("/users/list")
    private ResponseEntity<List<Users>> getalluser(){
        return ResponseEntity.ok().body(this.userDetailsServ.getalluser());
    }

    @GetMapping("/auth/getuserbyid/{id}")
    private ResponseEntity<Users> getuserbyid(@PathVariable int id){
        return ResponseEntity.ok().body(this.userDetailsServ.getuserbyid(id));
    }




   




}
