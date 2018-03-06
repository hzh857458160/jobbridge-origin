package com.jobBridge.service;

import com.jobBridge.Dao.ICollectTagDao;
import com.jobBridge.model.CollectTag;
import com.jobBridge.util.SqlSessionUtil;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by HanrAx on 2017/7/21.
 */
public class CollectTagService implements ICollectTagDao {
    private SqlSessionFactory sessionFactory;
    public CollectTagService() {
        sessionFactory = SqlSessionUtil.sqlSessionFactoryBuild();
    }

    @Override
    public List<CollectTag> findCollectTagByStudentId(Long studentId) {
        String statement = "collectTagMapper.findCollectTagByStudentId";
        List<Object> list = SqlSessionUtil.selectListOp(statement,studentId,sessionFactory);
        if(list == null || list.isEmpty()){
            return null;
        }
        else{
            List<CollectTag> collectTagList = new ArrayList<>();
            for(Object object:list){
                collectTagList.add((CollectTag)object);
            }
            return collectTagList;
        }
        /*List<CollectTag> list = null;
        try{
            session = sessionFactory.openSession();
            list = session.selectList(statement,studentId);
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            session.close();
        }
        return list;*/
    }

    @Override
    public void addCollectTag(CollectTag collectTag) {
        String statement = "collectTagMapper.addCollectTag";
        SqlSessionUtil.insertOp(statement,collectTag,sessionFactory);
    }

    @Override
    public void deleteCollectTagByStudentId(Long studentId){
        String statement = "collectTagMapper.deleteCollectTagByStudentId";
        SqlSessionUtil.deleteOp(statement,studentId,sessionFactory);
    }

    @Override
    public void deleteCollectTagById(Map<String,Object> map) {
        String statement = "collectTagMapper.deleteCollectTagById";
        SqlSessionUtil.deleteOp(statement,map,sessionFactory);
        /*try{
            session = sessionFactory.openSession();
            int result = session.delete(statement,map);
            session.commit();  //一定要记得commit
            if(result > 0){
                System.out.println("删除收藏成功");
            }else{
                System.out.println("删除收藏失败");
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            session.close();
        }*/
    }

}
