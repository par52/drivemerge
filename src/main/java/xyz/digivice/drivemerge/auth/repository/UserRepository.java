package xyz.digivice.drivemerge.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.digivice.drivemerge.auth.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
