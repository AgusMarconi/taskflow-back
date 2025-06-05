package com.example.taskFlow.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import java.util.Date;
import com.example.taskFlow.enumeration.Status;
import com.example.taskFlow.enumeration.Priority;

@Entity
public class Task{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String title;

    @Column
    private String description;

    @Column
    private Status status;

    @Column
    private Priority priority;

    @Column
    private Date created_at;

    @Column
    private Date updated_at;

    @Column
    private Date finished_at;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}