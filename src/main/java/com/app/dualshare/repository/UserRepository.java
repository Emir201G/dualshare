package com.app.dualshare.repository;

import com.app.dualshare.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    Optional<User> findByFirebaseCode(String firebaseCode);

    Optional<User> findByShareCode(String shareCode);
}
