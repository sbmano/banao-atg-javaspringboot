package com.Banao.Authentication.service;

import com.Banao.Authentication.model.Role;
import com.Banao.Authentication.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RoleServiceImpl {

    @Autowired
    private RoleRepository roleRepository;

    public Role addrole(Role role){
        return this.roleRepository.save(role);
    }
    public Role findbyname(String role){
        return this.roleRepository.findByName(role);
    }

}
