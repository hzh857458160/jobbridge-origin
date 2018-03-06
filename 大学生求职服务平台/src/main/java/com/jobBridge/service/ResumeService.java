package com.jobBridge.service;

import com.jobBridge.Dao.IResumeDao;
import com.jobBridge.model.Resume;
import com.jobBridge.model.Student;
import com.jobBridge.util.SqlSessionUtil;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;

/**
 * Created by SYunk on 2017/7/21.
 */
public class ResumeService implements IResumeDao {

    private SqlSessionFactory sessionFactory;
    private SqlSession session;
    public ResumeService() {
        String resource = "mybatisConf.xml";
        try {
            Reader reader = Resources.getResourceAsReader(resource);
            sessionFactory = new SqlSessionFactoryBuilder().build(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Long findResumeIdByStudentId(Long studentId){
        String statement = "resumeMapper.findResumeIdByStudentId";
        Long resumeId = null;
        try{
            session = sessionFactory.openSession();
            resumeId = session.selectOne(statement,studentId);
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            session.close();
        }
        return resumeId;
    }

    @Override
    public Resume findResumeByResumeId(Long resumeId) {
        String statement = "resumeMapper.findResumeByResumeId";
        Object object = SqlSessionUtil.selectOp(statement,resumeId,sessionFactory);
        if(object instanceof Resume){
            return (Resume)object;
        }else{
            return null;
        }

    }

    public void addResume(Resume resume){
        String statement = "resumeMapper.addResume";
        try{
            session = sessionFactory.openSession();
            int result = session.insert(statement,resume);
            session.commit();  //一定要记得commit
            if(result > 0){
                System.out.println("学生对应简历添加成功");
            }else{
                System.out.println("学生对应简历添加失败");
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            session.close();
        }
    }
}
