package com.wf.domain;

import java.io.Serializable;
import java.util.Date;

public class Student implements Serializable {
    private Integer id;
    private Integer sNumber;
    private String name;
    private Integer age;
    private String sex;
    private Integer classes;
    private String grade;
    private String profession;
    private String academy;
    private String tName;
    private String phone;
    private String email;
    private Date birthday;
    private String location;

    public Student() {
    }

    public Student(Integer sNumber, String name, Integer age, String sex, Integer classes, String grade, String profession, String academy, String tName, String phone, String email, Date birthday, String location) {
        this.sNumber = sNumber;
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.classes = classes;
        this.grade = grade;
        this.profession = profession;
        this.academy = academy;
        this.tName = tName;
        this.phone = phone;
        this.email = email;
        this.birthday = birthday;
        this.location = location;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getsNumber() {
        return sNumber;
    }

    public void setsNumber(Integer sNumber) {
        this.sNumber = sNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getClasses() {
        return classes;
    }

    public void setClasses(Integer classes) {
        this.classes = classes;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getAcademy() {
        return academy;
    }

    public void setAcademy(String academy) {
        this.academy = academy;
    }

    public String gettName() {
        return tName;
    }

    public void settName(String tName) {
        this.tName = tName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", sNumber=" + sNumber +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                ", classes=" + classes +
                ", grade='" + grade + '\'' +
                ", profession='" + profession + '\'' +
                ", academy='" + academy + '\'' +
                ", tName='" + tName + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", birthday=" + birthday +
                ", location='" + location + '\'' +
                '}';
    }
}
