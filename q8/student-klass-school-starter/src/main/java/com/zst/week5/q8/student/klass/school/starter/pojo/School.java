package com.zst.week5.q8.student.klass.school.starter.pojo;

import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

public class School {
    @Autowired
    private Klass klass;

    @Resource(name = "student100")
    Student student100;

    @Override
    public String toString() {
        return "School{" +
                "klass=" + klass +
                ", student100=" + student100 +
                '}';
    }

    public void ding(){
        System.out.println("Class1 have " + this.klass.getStudents().size() + " students and one is " + this.student100);
    }
}
