import com.wf.dao.StudentMapper;
import com.wf.domain.Student;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.Date;

public class TestMain {
    private InputStream resourceStream;
    private StudentMapper studentMapper;
    private SqlSession sqlSession;

    @Before
    public void init() throws Exception{
        resourceStream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sessionFactory = sqlSessionFactoryBuilder.build(resourceStream);
        sqlSession = sessionFactory.openSession();
        studentMapper = sqlSession.getMapper(StudentMapper.class);
    }

    @After
    public void afterOperation() throws Exception{
        sqlSession.commit();
        sqlSession.close();
        resourceStream.close();
    }

    /**
     * 保存学生
     */
    @Test
    public void saveStudent(){
        Student student = new Student();
        student.setsNumber(111111);
        student.setName("test");
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
        System.out.println(student);
        studentMapper.saveStudent(student);

        System.out.println(student.getId());
    }

    /**
     * 根据ID删除学生信息
     */
    @Test
    public void deleteStudentByName(){
//        int studentCount = studentMapper.getStudentCount();
//        System.out.println(studentCount);
        studentMapper.deleteStudentByName("test");
    }

    /**
     * 根据name更新学生信息
     */
    @Test
    public void updateStudentByName(){
        Student student = new Student();
        student.setName("test");
        student.setLocation("month");
        studentMapper.updateStudentByName(student);
    }

    /**
     * 获取学生总数目
     */
    @Test
    public void getStudentNumber(){
        int studentCount = studentMapper.getStudentCount();
        System.out.println(studentCount);
    }

}
