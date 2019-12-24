package com.wf.dao;

import com.wf.domain.Student;

public interface StudentMapper {
    /**
     * 保存学生信息
     * @param student
     */
    void saveStudent(Student student);

    /**
     * 根据姓名删除学生信息
     * @param name
     */
    void deleteStudentByName(String name);

    /**
     * 根据姓名修改学生信息
     * @param student
     */
    void updateStudentByName(Student student);

    /**
     * 根据id查询学生信息
     * @param id
     * @return
     */
    Student getStudentById(Integer id);

    /**
     *查询学生信息个数
     * @return
     */
    int getStudentCount();
}
