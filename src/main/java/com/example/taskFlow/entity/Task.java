package com.example.taskFlow.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.util.Date;

import com.example.taskFlow.ENUM.Priority;
import com.example.taskFlow.ENUM.Status;

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
    private Status status; //is ENUM

    @Column
    private Priority priority; //is ENUM

    @Column
    private Date created_at;

    @Column
    private Date updated_at;

    @Column
    private Date finished_at;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName= "id", nullable = false)
    @Column
    private User user;

}