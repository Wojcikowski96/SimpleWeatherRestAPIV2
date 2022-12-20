package com.example.task.users.api;

import com.example.task.users.Privilage;
import com.example.task.users.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PrivilageRepository extends JpaRepository<Privilage, Long> {
    @Query("SELECT s FROM Privilage s WHERE s.name = ?1")
    Privilage findByName(String name);
}
