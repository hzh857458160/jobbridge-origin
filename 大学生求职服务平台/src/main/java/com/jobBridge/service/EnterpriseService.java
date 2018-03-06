package com.jobBridge.service;

import com.jobBridge.Dao.IEnterpriseDao;
import com.jobBridge.model.Enterprise;
import com.jobBridge.util.SqlSessionUtil;

import org.apache.ibatis.session.SqlSessionFactory;


/**
 * Created by SYunk on 2017/7/20.
 */
public class EnterpriseService implements IEnterpriseDao {

    private SqlSessionFactory sessionFactory;

    public EnterpriseService() {
        sessionFactory = SqlSessionUtil.sqlSessionFactoryBuild();
    }

    public Enterprise findEnterpriseById(Long enterpriseId){
        String statement = "enterpriseMapper.findEnterpriseById";
        Object object = SqlSessionUtil.selectOp(statement,enterpriseId,sessionFactory);
        if(object instanceof Enterprise){
            return (Enterprise)object;
        }else{
            return null;
        }
    }
    public Enterprise findEnterpriseByUserName(String userName){
        String statement = "enterpriseMapper.findEnterpriseByUserName";
        Object object = SqlSessionUtil.selectOp(statement,userName,sessionFactory);
        if(object instanceof Enterprise){
            return (Enterprise)object;
        }else{
            return null;
        }
    }
    public Enterprise findEnterpriseByMailbox(String mailbox){
        String statement = "enterpriseMapper.findEnterpriseByMailbox";
        Object object = SqlSessionUtil.selectOp(statement,mailbox,sessionFactory);
        if(object instanceof Enterprise){
            return (Enterprise)object;
        }else{
            return null;
        }
    }
    public void addEnterprise(Enterprise enterprise) {
        String statement = "enterpriseMapper.addEnterprise";
        SqlSessionUtil.insertOp(statement,enterprise,sessionFactory);
    }
}
