package com.jobBridge.service;

import com.jobBridge.Dao.IDeliverDao;
import com.jobBridge.model.Deliver;
import com.jobBridge.util.SqlSessionUtil;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by HanrAx on 2017/7/21.
 */
public class DeliverService implements IDeliverDao{
    private SqlSessionFactory sessionFactory;
    private SqlSession session;
    public DeliverService() {
       sessionFactory = SqlSessionUtil.sqlSessionFactoryBuild();
    }

    @Override
    public List<Deliver> findDeliverByResumeIdAndEnterpriseId(Map<String, Object> map) {
            String statement = "deliverMapper.findDeliverByResumeIdAndEnterpriseId";
            List<Object> list = SqlSessionUtil.selectListOp(statement,map,sessionFactory);
            if(list == null || list.isEmpty()){
                return null;
            }else{
                List<Deliver> deliverList = new ArrayList<>();
                for(Object object:list){
                    deliverList.add((Deliver) object);
                }
                return deliverList;
            }
           /* List<Deliver> list = null;
            try{
                session = sessionFactory.openSession();
                list = session.selectList(statement,map);
            }catch(Exception e){
                e.printStackTrace();
            }finally {
                session.close();
            }
            return list;*/
    }

    @Override
    public List<Deliver> findDeliverByEnterpriseId(Long enterpriseId) {
        String statement = "deliverMapper.findDeliverByEnterpriseId";
//        SqlSessionUtil.selectListOp(statement,enterpriseId,sessionFactory);
        List<Deliver> list = null;
        try{
            session = sessionFactory.openSession();
            list = session.selectList(statement,enterpriseId);
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            session.close();
        }
        return list;
    }

    @Override
    public  Deliver findDeliverById(Long deliverId){
        String statement = "deliverMapper.findDeliverById";
        Object object = SqlSessionUtil.selectOp(statement,deliverId,sessionFactory);
        if(object instanceof  Deliver){
            return (Deliver)object;
        }else{
            return null;
        }
    }

    @Override
    public List<Deliver> findDeliverByResumeId(Long resumeId){
        String statement = "deliverMapper.findDeliverByResumeId";
//        SqlSessionUtil.selectListOp(statement,enterpriseId,sessionFactory);
        List<Deliver> list = null;
        try{
            session = sessionFactory.openSession();
            list = session.selectList(statement,resumeId);
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            session.close();
        }
        return list;
    }

    @Override
    public void updateHaveDeleteByDeliverId(Long deliverId){
        String statement = "deliverMapper.updateHaveDeleteByDeliverId";
        SqlSessionUtil.updateOp(statement,deliverId,sessionFactory);
    }

    @Override
    public void addDeliver(Deliver deliver) {
        String statement = "deliverMapper.addDeliver";
        SqlSessionUtil.insertOp(statement,deliver,sessionFactory);
    }
}
