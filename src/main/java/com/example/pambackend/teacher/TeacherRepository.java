package com.example.pambackend.teacher;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher,Long> {

    @Query("select t from Teacher t where t.username = :username and t.password = :password")
    Optional<Teacher> findByNameAndPassword(String username, String password);
}
