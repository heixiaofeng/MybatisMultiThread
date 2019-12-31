package com.wf.service;

import com.wf.dao.StudentMapper;
import com.wf.domain.DMLCount;
import com.wf.domain.DMLTime;
import com.wf.domain.Student;
import com.wf.utils.DBUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.Date;

public class WorkerThreadService implements Runnable{
    private DMLCount dmlCount;
    private DMLTime dmlTime;
    private Student student;
    private int operationCount;


    public WorkerThreadService(DMLCount dmlCount, DMLTime dmlTime, int operationCount) {
        this.dmlCount = dmlCount;
        this.dmlTime = dmlTime;
        this.operationCount = operationCount;

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
        SqlSession sqlSession = DBUtils.getSession();
        StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);
        while(true) {
            double startTime = System.currentTimeMillis();
            //每秒插入次数
            studentMapper.saveStudent(student);
            dmlCount.addCount();
            if (dmlCount.getCount() <= operationCount) {
                sqlSession.commit();
                //单次操作时间
                double endTime = System.currentTimeMillis();
                double time = (endTime - startTime) / 1000;
                dmlTime.addElement(time);
            } else {
                dmlCount.cutCount();
            }
        }
    }
}
