package xyz.digivice.drivemerge.auth.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import xyz.digivice.drivemerge.auth.dto.UserDTO;
import xyz.digivice.drivemerge.auth.entity.Role;
import xyz.digivice.drivemerge.auth.entity.User;
import xyz.digivice.drivemerge.auth.repository.RoleRepository;
import xyz.digivice.drivemerge.auth.repository.UserRepository;
import xyz.digivice.drivemerge.auth.service.UserService;

import java.util.Arrays;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void saveUser(UserDTO userDTO) {
        User user = new User();
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPassword( passwordEncoder.encode(userDTO.getPassword()) );

        String roleAdmin = "ROLE_ADMIN";
        Role role = roleRepository.findByName(roleAdmin);
        if(role == null){
            role = createRole(roleAdmin);
        }

        user.setRoles(Arrays.asList(role));
        userRepository.save(user);
    }

    private Role createRole(String name) {
        Role role = new Role();
        role.setName(name);
        return roleRepository.save(role);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
