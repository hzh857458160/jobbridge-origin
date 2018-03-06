package com.jobBridge.service;

import com.jobBridge.Dao.ICollectEnterpriseDao;
import com.jobBridge.model.CollectEnterprise;
import com.jobBridge.util.SqlSessionUtil;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by HanrAx on 2017/7/20.
 */
public class CollectEnterpriseService implements ICollectEnterpriseDao{

    private SqlSessionFactory sessionFactory;

    public CollectEnterpriseService() {
        sessionFactory = SqlSessionUtil.sqlSessionFactoryBuild();
    }

    @Override
    public List<CollectEnterprise> findCollectEnterpriseByStudentId(Long studentId) {
        String statement = "collectEnterpriseMapper.findCollectEnterpriseByStudentId";
        List<Object> list = SqlSessionUtil.selectListOp(statement,studentId,sessionFactory);
        if(list == null || list.isEmpty()){
            return null;
        }
        else{
            List<CollectEnterprise> collectEnterpriseList = new ArrayList<>();
            for(Object object:list){
                collectEnterpriseList.add((CollectEnterprise)object);
            }
            return collectEnterpriseList;
        }
        /*List<CollectEnterprise> list = null;
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
    public void addCollectEnterprise(CollectEnterprise collectEnterprise) {
        String statement = "collectEnterpriseMapper.addCollectEnterprise";
        SqlSessionUtil.insertOp(statement,collectEnterprise,sessionFactory);
    }

    @Override
    public void deleteCollectEnterpriseById(Map<String,Object> map) {
        String statement = "collectEnterpriseMapper.deleteCollectEnterpriseById";
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
