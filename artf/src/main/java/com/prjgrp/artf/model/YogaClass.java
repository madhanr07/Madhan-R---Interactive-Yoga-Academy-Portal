package com.prjgrp.artf.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import javax.persistence.*;
@Data
@Entity
public class YogaClass {    
    @Id    
    @GeneratedValue(strategy = GenerationType.IDENTITY)    
    private Long id;    
    private String name;    
    private String description;    
    @ManyToOne    
    @JoinColumn(name = "user_id")    
    @JsonIgnore    
    private User user;
}