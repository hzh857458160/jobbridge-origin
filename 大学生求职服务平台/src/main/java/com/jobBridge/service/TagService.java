package com.jobBridge.service;

import com.jobBridge.Dao.ITagDao;
import com.jobBridge.model.Tag;
import com.jobBridge.util.SqlSessionUtil;
import org.apache.ibatis.session.SqlSessionFactory;

/**
 * Created by Administrator on 2017/7/24 0024.
 */
public class TagService implements ITagDao {
    private SqlSessionFactory sessionFactory;

    public TagService(){
        sessionFactory = SqlSessionUtil.sqlSessionFactoryBuild();
    }

    @Override
    public Tag findTagByName(String name){
        String statement = "tagMapper.findTagByName";
        Object object = SqlSessionUtil.selectOp(statement,name,sessionFactory);
        if(object instanceof Tag){
            return (Tag) object;
        }else{
            return null;
        }
    }

    @Override
    public Tag findTagByTagId(Integer tagId) {
        String statement = "tagMapper.findTagByTagId";
        Tag tag = (Tag)SqlSessionUtil.selectOp(statement,tagId,sessionFactory);
        return tag;
    }

    @Override
    public void addTag(Tag tag){
        String statement = "tagMapper.addTag";
        SqlSessionUtil.insertOp(statement,tag,sessionFactory);
    }
}
