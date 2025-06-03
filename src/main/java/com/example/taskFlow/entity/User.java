package com.example.taskFlow.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;
import org.springframework.scheduling.config.Task;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

    @Column
    private String password_hash;

    @OneToMany(fetch= FetchType.LAZY)
    @Column
    //@Builder.Default
    private List<Task> tasks/* = new ArrayList<>()*/;

}