package xyz.digivice.drivemerge.auth.service;

import xyz.digivice.drivemerge.auth.dto.UserDTO;
import xyz.digivice.drivemerge.auth.entity.User;

public interface UserService {
    void saveUser(UserDTO userDTO);

    User findByEmail(String email);
}
