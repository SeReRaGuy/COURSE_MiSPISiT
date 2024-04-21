package com.example.course.Models;

public class Departments {
    private int id;
    private String name;
    private int head;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHead() {
        return head;
    }

    public void setHead(int head) {
        this.head = head;
    }

    public Departments(int id, String name, int head) {
        this.id = id;
        this.name = name;
        this.head = head;
    }
}
