package com.jobBridge.Dao;

import com.jobBridge.model.RecruitInfo;

import java.util.List;
import java.util.Map;

/**
 * Created by SYunk on 2017/7/21.
 */
public interface IRecruitInfoDao {
    RecruitInfo findRecruitInfoById(Long recruitInfoId);
    List<RecruitInfo> findRecruitInfoByEnterpriseId(Long enterpriseId);
    RecruitInfo findLastRecruitInfoByEnterpriseId(Long enterpriseId);
    List<RecruitInfo> findRecruitInfoByLocation(String location);
    void updateHaveDeleteById(Long recruitInfoId);
    int findNumOfRecruitInfo();
    List<RecruitInfo> findRecruitInfoOrderByTime(Map<String,Object> map);
    void addRecruitInfo(RecruitInfo recruitInfo);
    void updateRecruitInfoById(Map<String,Object> map);
    void deleteRecruitInfoById(Long recruitInfoId);
}
