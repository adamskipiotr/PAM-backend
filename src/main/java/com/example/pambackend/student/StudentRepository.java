package com.example.pambackend.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {

    @Query("select s from Student s where s.studentID = :studentID")
     Student findByID(@Param("studentID")Long studentID);

    @Query("select s from Student s where s.username = :username and s.password = :password")
    Optional<Student> findByNameAndPassword(@Param("username")String username, @Param("password") String password);

    @Query("select s from Student s where s.username = :studentName")
    Student findByName(@Param("studentName")String studentName);
}
