package com.example.pambackend.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {

    @Query("select s from Student s where s.studentID = :studentID")
    public Student findByID(@Param("studentID")Long studentID);

    @Query("select s from Student s where s.username = :username and s.password = :password")
    void findByNameAndPassword(@Param("username")String username,@Param("password") String password);
}