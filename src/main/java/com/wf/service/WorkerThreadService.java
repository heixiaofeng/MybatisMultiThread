package com.wf.service;

import com.wf.dao.StudentMapper;
import com.wf.domain.DMLCount;
import com.wf.domain.DMLTime;
import com.wf.domain.Student;
import com.wf.utils.DBUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.Date;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class WorkerThreadService implements Runnable{
    SqlSession sqlSession;
    private DMLCount dmlCount;
    private DMLTime dmlTime;
    private Student student;
    private Lock lock = new ReentrantLock();


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
        sqlSession = DBUtils.getSession();
        StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);
        while(true){
            lock.lock();
            try {
                //终止循环
                System.out.println("thread name:" + Thread.currentThread().getName() + ", count:" + dmlCount.getCount());
                if (dmlCount.getCount() == 1000)
                    break;
                double startTime = System.currentTimeMillis();
                //每秒插入次数
                studentMapper.saveStudent(student);
                sqlSession.commit();
                dmlCount.addCount();
                //单次操作时间
                double endTime = System.currentTimeMillis();
                double time = (endTime - startTime) / 1000;
                dmlTime.addElement(time);
            } catch (Exception e){
                e.printStackTrace();
            }
            finally {
                lock.unlock();
            }
        }
    }
}
