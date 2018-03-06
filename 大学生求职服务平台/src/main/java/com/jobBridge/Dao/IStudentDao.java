package com.jobBridge.Dao;

import com.jobBridge.model.Student;

/**
 * Created by SYunk on 2017/7/19.
 */
public interface IStudentDao {
    Student findStudentByUserName(String userName); //根据用户名查找学生
    Student findStudentByStudentId(Long studentId);  //通过学生ID查找学生
    Student findStudentByMailbox(String mailbox); //根据邮箱查找学生
    void addStudent(Student student);  //新增注册的学生账号
}
