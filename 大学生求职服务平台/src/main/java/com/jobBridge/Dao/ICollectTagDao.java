package com.jobBridge.Dao;

import com.jobBridge.model.CollectTag;

import java.util.List;
import java.util.Map;

/**
 * Created by HanrAx on 2017/7/21.
 */
public interface ICollectTagDao {
    List<CollectTag> findCollectTagByStudentId(Long studentId);     //学生查询所有自己收藏过的便签
    void addCollectTag(CollectTag collectTag);                     //新增对标签的收藏
    void deleteCollectTagByStudentId(Long studentId);
    void deleteCollectTagById(Map<String, Object> map);              //通过传入包含学生id与标签id的map来删除该标签收藏
}
