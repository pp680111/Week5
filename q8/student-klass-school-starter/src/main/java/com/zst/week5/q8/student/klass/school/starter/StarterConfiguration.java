package com.zst.week5.q8.student.klass.school.starter;

import com.zst.week5.q8.student.klass.school.starter.pojo.Klass;
import com.zst.week5.q8.student.klass.school.starter.pojo.School;
import com.zst.week5.q8.student.klass.school.starter.pojo.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;
import java.util.stream.Collectors;

@Configuration
public class StarterConfiguration {
    @Autowired
    private ApplicationContext applicationContext;

    @Bean
    public Student student123() {
        return new Student(123, "KK123");
    }

    @Bean
    public Student student100() {
        return new Student(100, "KK100");
    }

    @Bean
    public Klass class1() {
        Map<String, Student> stuBeans = applicationContext.getBeansOfType(Student.class);
        Klass klass = new Klass();
        klass.setStudents(stuBeans.entrySet()
                .stream().map(Map.Entry::getValue).collect(Collectors.toList()));
        return klass;
    }

    @Bean
    public School school() {
        /*
            手动创建处school对象，并通过autowireCapableBeanFactory.autowireBean方法处理School类中需要自动注入的
            对象，再通过@Bean注解让Spring把方法返回的school对象放入ApplicationContext中进行管理
         */
        School school = new School();
        applicationContext.getAutowireCapableBeanFactory().autowireBean(school);
        return school;
    }
}
