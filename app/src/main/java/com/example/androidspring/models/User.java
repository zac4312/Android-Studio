package com.example.androidspring.models;

public class User {

    private long Id;
    private String name;
    private int age;

    public User(){}

    public User(String name, int age){
        this.name = name;
        this.age = age;
    }

    public String getName(){

        return name;
    }

    public void setName(String name) { // ✅ Add this setter
        this.name = name;
    }


    public int getAge(){

        return age;
    }

    public void setAge(int age) { // ✅ Add this setter
        this.age = age;
    }


    public Long getId() {
        return Id;
    }

    public void setId(Long id) { // ✅ Add this setter
        this.Id = id;
    }


}
