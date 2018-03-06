package com.jobBridge.Dao;

import com.jobBridge.model.Deliver;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

/**
 * Created by HanrAx on 2017/7/21.
 */
public interface IDeliverDao {
    /**
     * 通过简历ID、公司ID、查找所有的投递信息
     * @param map
     * @return
     */
    List<Deliver> findDeliverByResumeIdAndEnterpriseId(Map<String, Object> map);
    //公司查找所有收到的投递信息，并可以根据其中的简历查看该学生的简历
    List<Deliver> findDeliverByEnterpriseId(Long enterpriseId);
    //根据投递号查询投递信息
    Deliver findDeliverById(Long deliverId);
    //根据简历号查询投递信息
    List<Deliver> findDeliverByResumeId(Long resumeId);
    //根据投递号修改字段
    void updateHaveDeleteByDeliverId(Long deliverId);
    //新建投递信息
    void addDeliver(Deliver deliver);

}
