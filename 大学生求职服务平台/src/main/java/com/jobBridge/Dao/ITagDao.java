package com.jobBridge.Dao;

import com.jobBridge.model.Tag;

/**
 * Created by Administrator on 2017/7/24 0024.
 */
public interface ITagDao {
    Tag findTagByTagId(Integer tagId);   //通过tagId找到标签实体
    Tag findTagByName(String name);
    void addTag(Tag tag);
}
