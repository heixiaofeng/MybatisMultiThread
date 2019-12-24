package com.wf.service;

import com.wf.dao.StudentMapper;
import com.wf.domain.DMLCount;
import com.wf.domain.DMLTime;
import com.wf.domain.Student;
import com.wf.utils.DBUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.Date;

public class WorkerThreadService implements Runnable{
    SqlSession sqlSession;
    private DMLCount dmlCount;
    private DMLTime dmlTime;
    private Student student;


    public WorkerThreadService(DMLCount dmlCount, DMLTime dmlTime){
        this.dmlCount = dmlCount;
        this.dmlTime = dmlTime;

        student = new Student();
        student.setsNumber(111111);
        student.setName("insert");
        student.setAge(18);
        student.setSex("男");
        student.setAcademy("史莱克学院");
        student.setClasses(5);
        student.setGrade("研二");
        student.setEmail("123@gmail.com");
        student.setBirthday(new Date());
        student.setLocation("earth");
        student.setPhone("123456");
        student.setProfession("软件工程");
        student.settName("光");
    }

    @Override
    public void run() {
        while (dmlCount.getFlag()){
            long startTime = System.currentTimeMillis();
            sqlSession = DBUtils.getSession();
            StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);
            studentMapper.saveStudent(student);
            dmlCount.setCount();
            if(dmlCount.getCount() == 200){
                dmlCount.setFlag(false);
            }
            sqlSession.commit();
            sqlSession.close();

            long endTime = System.currentTimeMillis();
            long time = (endTime-startTime)/1000;
            dmlTime.addElement(time);
        }
    }
}
