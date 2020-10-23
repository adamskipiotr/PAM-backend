package com.example.pambackend.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<PAMUser,Long> {

    @Query("select u from PAMUser u where u.username = :username and u.password = :password")
    void findUser(@Param("username") String username,@Param("password") String password);
}
