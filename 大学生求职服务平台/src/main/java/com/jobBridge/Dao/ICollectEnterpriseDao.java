package com.jobBridge.Dao;

import com.jobBridge.model.CollectEnterprise;

import java.util.List;
import java.util.Map;

/**
 * Created by HanrAx on 2017/7/20.
 */
public interface ICollectEnterpriseDao {
    List<CollectEnterprise> findCollectEnterpriseByStudentId(Long studentId);    //学生查询自己收藏的所有公司
    void addCollectEnterprise(CollectEnterprise collectEnterprise);   //学生添加一个新的收藏
    void deleteCollectEnterpriseById(Map<String, Object> map);         //通过传入学生ID与公司ID
}
