package com.example.task.repository;

import com.example.task.users.Role;
import com.example.task.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    @Query("SELECT s FROM Role s WHERE s.name = ?1")
    Optional<Role> findByName(String name);


}
