package com.jobBridge.service;

import com.jobBridge.Dao.IResumeDetailDao;
import com.jobBridge.model.*;
import com.jobBridge.util.SqlSessionUtil;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/7/27 0027.
 */
public class ResumeDetailService implements IResumeDetailDao {
    private SqlSessionFactory sessionFactory;

    public ResumeDetailService() {
        sessionFactory = SqlSessionUtil.sqlSessionFactoryBuild();
    }

    @Override
    public ResumeHead findResumeHeadByResumeId(Long resumeId) {
        String statement = "resumeDetailMapper.findResumeHeadByResumeId";
        Object object = SqlSessionUtil.selectOp(statement,resumeId,sessionFactory);
        if(object instanceof ResumeHead){
            return (ResumeHead)object;
        }else{
            return null;
        }

    }

    @Override
    public void addResumeHead(ResumeHead resumeHead) {
        String statement = "resumeDetailMapper.addResumeHead";
        SqlSessionUtil.insertOp(statement,resumeHead,sessionFactory);
    }

    @Override
    public void deleteResumeHeadByResumeId(Long resumeId) {
        String statement = "resumeDetailMapper.deleteResumeHeadByResumeId";
        SqlSessionUtil.deleteOp(statement,resumeId,sessionFactory);
    }

    @Override
    public List<ResumeEducation> findResumeEducationByResumeId(Long resumeId) {
        String statement = "resumeDetailMapper.findResumeEducationByResumeId";
        List<Object> list = SqlSessionUtil.selectListOp(statement,resumeId,sessionFactory);
        if(list == null || list.isEmpty()){
            return null;
        }
        else{
            List<ResumeEducation> resumeEducationList = new ArrayList<>();
            for(Object object:list){
                resumeEducationList.add((ResumeEducation)object);
            }
            return resumeEducationList;
        }
    }

    @Override
    public void addResumeEducation(ResumeEducation resumeEducation) {
        String statement = "resumeDetailMapper.addResumeEducation";
        SqlSessionUtil.insertOp(statement,resumeEducation,sessionFactory);
    }

    @Override
    public void deleteResumeEducationByResumeId(Long resumeId) {
        String statement = "resumeDetailMapper.deleteResumeEducationByResumeId";
        SqlSessionUtil.deleteOp(statement,resumeId,sessionFactory);
    }

    @Override
    public List<ResumeWorks> findResumeWorksByResumeId(Long resumeId) {
        String statement = "resumeDetailMapper.findResumeWorksByResumeId";
        List<Object> list = SqlSessionUtil.selectListOp(statement,resumeId,sessionFactory);
        if(list == null || list.isEmpty()){
            return null;
        }
        else{
            List<ResumeWorks> resumeWorksList = new ArrayList<>();
            for(Object object:list){
                resumeWorksList.add((ResumeWorks)object);
            }
            return resumeWorksList;
        }
    }

    @Override
    public void addResumeWorks(ResumeWorks resumeWorks) {
        String statement = "resumeDetailMapper.addResumeWorks";
        SqlSessionUtil.insertOp(statement,resumeWorks,sessionFactory);
    }

    @Override
    public void deleteResumeWorksByResumeId(Long resumeId) {
        String statement = "resumeDetailMapper.deleteResumeWorksByResumeId";
        SqlSessionUtil.deleteOp(statement,resumeId,sessionFactory);
    }

    @Override
    public List<ResumeOrganization> findResumeOrganizationByResumeId(Long resumeId) {
        String statement = "resumeDetailMapper.findResumeOrganizationByResumeId";
        List<Object> list = SqlSessionUtil.selectListOp(statement,resumeId,sessionFactory);
        if(list == null || list.isEmpty()){
            return null;
        }
        else{
            List<ResumeOrganization> resumeOrganizationList = new ArrayList<>();
            for(Object object:list){
                resumeOrganizationList.add((ResumeOrganization)object);
            }
            return resumeOrganizationList;
        }
    }

    @Override
    public void addResumeOrganization(ResumeOrganization resumeOrganization) {
        String statement = "resumeDetailMapper.addResumeOrganization";
        SqlSessionUtil.insertOp(statement,resumeOrganization,sessionFactory);
    }

    @Override
    public void deleteResumeOrganizationByResumeId(Long resumeId) {
        String statement = "resumeDetailMapper.deleteResumeOrganizationByResumeId";
        SqlSessionUtil.deleteOp(statement,resumeId,sessionFactory);
    }

    @Override
    public List<ResumeClub> findResumeClubByResumeId(Long resumeId) {
        String statement = "resumeDetailMapper.findResumeClubByResumeId";
        List<Object> list = SqlSessionUtil.selectListOp(statement,resumeId,sessionFactory);
        if(list == null || list.isEmpty()){
            return null;
        }
        else{
            List<ResumeClub> resumeClubList = new ArrayList<>();
            for(Object object:list){
                resumeClubList.add((ResumeClub)object);
            }
            return resumeClubList;
        }
    }

    @Override
    public void addResumeClub(ResumeClub resumeClub) {
        String statement = "resumeDetailMapper.addResumeClub";
        SqlSessionUtil.insertOp(statement,resumeClub,sessionFactory);
    }

    @Override
    public void deleteResumeClubByResumeId(Long resumeId) {
        String statement = "resumeDetailMapper.deleteResumeClubByResumeId";
        SqlSessionUtil.deleteOp(statement,resumeId,sessionFactory);
    }

    @Override
    public ResumeSkill findResumeSkillByResumeId(Long resumeId) {
        String statement = "resumeDetailMapper.findResumeSkillByResumeId";
        Object object = SqlSessionUtil.selectOp(statement,resumeId,sessionFactory);
        if(object instanceof ResumeSkill){
            return (ResumeSkill)object;
        }else{
            return null;
        }
    }

    @Override
    public void addResumeSkill(ResumeSkill resumeSkill) {
        String statement = "resumeDetailMapper.addResumeSkill";
        SqlSessionUtil.insertOp(statement,resumeSkill,sessionFactory);
    }

    @Override
    public void deleteResumeSkillByResumeId(Long resumeId) {
        String statement = "resumeDetailMapper.deleteResumeSkillByResumeId";
        SqlSessionUtil.deleteOp(statement,resumeId,sessionFactory);
    }
}
