package com.jobBridge.Dao;

import com.jobBridge.model.InformInterview;

import java.util.List;

/**
 * Created by HanrAx on 2017/7/20.
 */
public interface IInformInterviewDao {
    InformInterview findInterviewById(Long interviewId);                    //通过通知的ID来查找该通知
    List<InformInterview> findInterviewsByEnterpriseId(Long enterpriseId);  //通过公司ID来查找所有公司发过的面试通知
    List<InformInterview> findInterviewsByStudentId(Long studentId);        //通过学生ID来查找所有学生收到的面试通知
    void addInterview(InformInterview informInterview);                     //新增一个面试请求，通过传入InformInterview实体
    void deleteInterviewById(Long informInterviewId);                       //通过面试ID删除该面试请求
}
