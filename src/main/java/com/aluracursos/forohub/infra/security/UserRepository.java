package com.aluracursos.forohub.infra.security;

import com.aluracursos.forohub.model.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<User,Long> {
    UserDetails findByLogin(String login);
}
