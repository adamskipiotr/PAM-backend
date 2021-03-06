package com.example.pambackend.message;

import com.example.pambackend.group.StudentsGroup;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message,Long> {

    @Query("SELECT m.contents FROM Message m WHERE m.recipientsStudentsGroup = :assignedGroup")
    List<String> getAllMessagesForGroup(@Param("assignedGroup")StudentsGroup  assignedGroup);

    @Query("SELECT m FROM Message m WHERE m.title = :title")
    Message findByTitle(@Param("title")String title);

    @Query("SELECT m FROM Message m WHERE m.author = :author")
    List<Message> findAllByAuthor(@Param("author")String author);
}
