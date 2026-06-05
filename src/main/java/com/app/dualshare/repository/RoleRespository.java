package com.app.dualshare.repository;

import com.app.dualshare.enums.RoleType;
import com.app.dualshare.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRespository extends JpaRepository<Role, Long> {
    Optional<Role> findByRoleType(RoleType roleType);
}
