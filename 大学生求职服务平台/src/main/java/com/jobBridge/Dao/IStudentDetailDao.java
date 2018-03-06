package com.jobBridge.Dao;

import com.jobBridge.model.StudentDetail;

/**
 * Created by SYunk on 2017/7/20.
 */
public interface IStudentDetailDao {
    StudentDetail findStudentDetailByStudentId(Long studentId);
    void addStudentDetail(StudentDetail studentDetail);
    void deleteStudentDetailByStudentId(Long studentId);
    void updateStudentDetailValidationByStudentId(Long studentId);//通过学生id，修改学生验证位

}
