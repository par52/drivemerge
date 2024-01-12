package xyz.digivice.drivemerge.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.digivice.drivemerge.auth.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
