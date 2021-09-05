package com.zst.week5.q8.student.klass.school.starter.pojo;

import java.util.List;

public class Klass {
    private List<Student> students;

    public void dong() {
        System.out.println(this.getStudents());
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return "Klass{" +
                "students=" + students +
                '}';
    }
}
