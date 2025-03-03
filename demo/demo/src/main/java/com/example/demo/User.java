package com.example.demo;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author stude
 */

import jakarta.persistence.*;

@Entity
@Table(name = "NB_database")
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    
    private String name;
    private int age;
    
    public User(){} 
    
    public User(String name, int age){
        this.name = name;
        this.age = age;
    }
    
    public long getId(){
        return Id;
    } public void setId(long id) {this.Id = id;}

    public String getName(){
        return name;
    } public void setName(String name) {this.name = name;}

    
    public int getAge(){
        return age;
    } public void setAge(int age) {this.age = age;}

}
