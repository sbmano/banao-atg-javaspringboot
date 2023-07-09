package com.Banao.Authentication.service;

import com.Banao.Authentication.exception.CustomException;
import com.Banao.Authentication.model.Role;
import com.Banao.Authentication.model.Users;
import com.Banao.Authentication.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService,UserDetailsServ {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private RoleServiceImpl roleService;
    @Override
    public UserDetails loadUserByUsername(String s) throws CustomException {

        Optional<Users> user = Optional.ofNullable(this.userRepository.findByEmail(s));
                if(user.isPresent()){
                    Users u=user.get();
                    return new User(u.getEmail(),u.getPassword(),getAuthority(u));
                }
                else
                    throw new CustomException("User Not Found with username: " + s);

    }

    public Set<SimpleGrantedAuthority> getAuthority(Users user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        return authorities;
    }

    public Users adduser(Users users){
        Role role = roleService.findbyname("ROLE_USER");
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(role);
        users.setRoles(roleSet);
        return this.userRepository.save(users);
    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<Users> getalluser(){
        List<Users> list=this.userRepository.findAll();
        return list;
    }

    public Users getuserbyid(int id){
        Optional<Users> user = this.userRepository.findById(id);
        if(user.isPresent()){
            return user.get();
        }
        else {
            throw new CustomException("user not found");
        }
    }

    public Users getuserbyemail(String s) {
        Optional<Users> user = Optional.ofNullable(this.userRepository.findByEmail(s));
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new CustomException("user not found");
        }
    }
}

