package com.example.demo.repository;

import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    @Query(value = "SELECT count(email) FROM USUARIO WHERE EMAIL = ?1 ", nativeQuery = true)
    int emailExist(String email);

    Optional<User> findOneByEmail(String email);

    @Query(value = "SELECT * FROM USUARIO WHERE id_usuario = ?1 ", nativeQuery = true)
    Optional<User> findById(String id);

    @Query(value = "SELECT token FROM USUARIO WHERE EMAIL = ?1 ", nativeQuery = true)
    String getToken(String email);

}
