package com.jobBridge.service;

import com.jobBridge.Dao.IRecruitInfoTagDao;
import com.jobBridge.model.RecruitInfoTag;
import com.jobBridge.util.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SYunk on 2017/7/25.
 */
public class RecruitInfoTagService implements IRecruitInfoTagDao {

    private SqlSessionFactory sessionFactory;
    private SqlSession session;
    public RecruitInfoTagService() {
        sessionFactory = SqlSessionUtil.sqlSessionFactoryBuild();
    }

    public List<RecruitInfoTag> findRecruitInfoTagByRecruitInfoId(Long recruitInfoId){
        String statement = "recruitInfoTagMapper.findRecruitInfoTagByRecruitInfoId";
        List<Object> list = SqlSessionUtil.selectListOp(statement,recruitInfoId,sessionFactory);
        if(list == null || list.isEmpty()){
            return null;
        }else{
            List<RecruitInfoTag> recruitInfoTagList = new ArrayList<>();
            for(Object object:list){
                recruitInfoTagList.add((RecruitInfoTag)object);
            }
            return recruitInfoTagList;
        }
    }
    public List<RecruitInfoTag> findRecruitInfoTagByTagId(Integer tagId){
        String statement = "recruitInfoTagMapper.findRecruitInfoTagByTagId";
        List<Object> list = SqlSessionUtil.selectListOp(statement,tagId,sessionFactory);
        if(list == null || list.isEmpty()){
            return null;
        }else{
            List<RecruitInfoTag> recruitInfoTagList = new ArrayList<>();
            for(Object object:list){
                recruitInfoTagList.add((RecruitInfoTag)object);
            }
            return recruitInfoTagList;
        }
    }
    public void addRecruitInfoTag(RecruitInfoTag recruitInfoTag){
        String statement = "recruitInfoTagMapper.addRecruitInfoTag";
        SqlSessionUtil.insertOp(statement,recruitInfoTag,sessionFactory);
    }
    public void deleteRecruitInfoTagByRecruitInfoId(Long recruitInfoId){
        String statement = "recruitInfoTagMapper.deleteRecruitInfoTagByRecruitInfoId";
        try{
            session = sessionFactory.openSession();
            int result = session.delete(statement,recruitInfoId);
            session.commit();
            if(result > 0){
                System.out.println("招聘信息标签删除成功：" + recruitInfoId);
            }else{
                System.out.println("招聘信息标签删除失败：" + recruitInfoId);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            session.close();
        }
    }
}
