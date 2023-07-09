package com.Banao.Authentication.controller;

import com.Banao.Authentication.exception.CustomException;
import com.Banao.Authentication.jwt.JwtUtil;
import com.Banao.Authentication.model.Users;
import com.Banao.Authentication.model.payloads.Authtoken;
import com.Banao.Authentication.model.payloads.Login;
import com.Banao.Authentication.model.payloads.UserInfoResponse;
import com.Banao.Authentication.service.UserDetailsImpl;
import com.Banao.Authentication.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping("/api")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Autowired
    private JwtUtil jwtUtil;


    @PostMapping("/auth/login")
    private ResponseEntity<List<Object>> authenticateuser(@RequestBody Login login) throws AuthenticationException {
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        login.getEmail(),
                        login.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final String token = jwtUtil.generateToken(authentication);
        List<Object> list=new ArrayList<>();
        if(!authentication.isAuthenticated()){
            System.out.println("not authenticated");
            list.add(false);
            return ResponseEntity.ok(list);
        }
        if(authentication.isAuthenticated()){
            Users us=this.userDetailsService.getuserbyemail(login.getEmail());
            list.add(new Authtoken(token));
            list.add(true);
            list.add(us);
        }
        else{
            list.add(false);
        }


        return ResponseEntity.ok(list);
    }


}
