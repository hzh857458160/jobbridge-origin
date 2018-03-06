package com.jobBridge.service;

import com.jobBridge.Dao.IStudentDetailDao;
import com.jobBridge.model.StudentDetail;
import com.jobBridge.util.SqlSessionUtil;

import org.apache.ibatis.session.SqlSessionFactory;

/**
 * Created by SYunk on 2017/7/20.
 */
public class StudentDetailService implements IStudentDetailDao {

    private SqlSessionFactory sessionFactory;

    public StudentDetailService() {
        sessionFactory = SqlSessionUtil.sqlSessionFactoryBuild();
    }

    public StudentDetail findStudentDetailByStudentId(Long studentId){
        String statement = "studentDetailMapper.findStudentDetailByStudentId";
        Object object = SqlSessionUtil.selectOp(statement,studentId,sessionFactory);
        if(object instanceof StudentDetail){
            return (StudentDetail)object;
        }else{
            return null;
        }
    }
    public void addStudentDetail(StudentDetail studentDetail){
        String statement = "studentDetailMapper.addStudentDetail";
        SqlSessionUtil.insertOp(statement,studentDetail,sessionFactory);
    }
    public void deleteStudentDetailByStudentId(Long studentId){
        String statement = "studentDetailMapper.deleteStudentDetailByStudentId";
        SqlSessionUtil.deleteOp(statement,studentId,sessionFactory);
    }

    @Override
    public void updateStudentDetailValidationByStudentId(Long studentId) {
        String statement = "studentDetailMapper.updateStudentDetailValidationByStudentId";
        SqlSessionUtil.updateOp(statement,studentId,sessionFactory);
    }

}
