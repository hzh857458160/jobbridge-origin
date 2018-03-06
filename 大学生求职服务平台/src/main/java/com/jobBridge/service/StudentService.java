package com.jobBridge.service;

import com.jobBridge.Dao.IStudentDao;
import com.jobBridge.model.Student;
import com.jobBridge.util.SqlSessionUtil;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;


/**
 * Created by SYunk on 2017/7/19.
 */
public class StudentService implements IStudentDao {
    private SqlSessionFactory sessionFactory;
    public StudentService() {
        sessionFactory = SqlSessionUtil.sqlSessionFactoryBuild();
    }

    public Student findStudentByUserName(String userName){
        String statement = "studentMapper.findStudentByUserName";
        Object object = SqlSessionUtil.selectOp(statement,userName,sessionFactory);
        if(object instanceof Student){
            return (Student)object;
        }else{
            return null;
        }
    }
    @Override
    public Student findStudentByStudentId(Long studentId) {
        String statement = "studentMapper.findStudentByStudentId";
        Object object = SqlSessionUtil.selectOp(statement,studentId,sessionFactory);
        if(object instanceof Student){
            return (Student)object;
        }else{
            return null;
        }
    }
    public Student findStudentByMailbox(String mailbox){
        String statement = "studentMapper.findStudentByMailbox";
        Object object = SqlSessionUtil.selectOp(statement,mailbox,sessionFactory);
        if(object instanceof Student){
            return (Student)object;
        }else{
            return null;
        }
    }
    public void addStudent(Student student) {
        String statement = "studentMapper.addStudent";
        SqlSessionUtil.insertOp(statement,student,sessionFactory);
    }
}
