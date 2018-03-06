package com.jobBridge.service;


import com.jobBridge.Dao.IManagerDao;
import com.jobBridge.model.Manager;
import com.jobBridge.util.SqlSessionUtil;

import org.apache.ibatis.session.SqlSessionFactory;

/**
 * Created by HanrAx on 2017/7/20.
 */
public class ManagerService implements IManagerDao {

    private SqlSessionFactory sessionFactory;

    public ManagerService() {
        sessionFactory = SqlSessionUtil.sqlSessionFactoryBuild();
    }

    @Override
    public Manager findManagerById(Integer managerId) {
        String statement = "managerMapper.findManagerById";
        Object object = SqlSessionUtil.selectOp(statement,managerId,sessionFactory);
        if(object instanceof Manager){
            return (Manager)object;
        }else{
            return null;
        }
    }
    @Override
    public Manager findManagerByUserName(String userName) {
        String statement = "managerMapper.findManagerByUserName";
        Object object = SqlSessionUtil.selectOp(statement,userName,sessionFactory);
        if(object instanceof Manager){
            return (Manager)object;
        }else{
            return null;
        }
    }
    @Override
    public void addManager(Manager manager) {
        String statement = "managerMapper.addManager";
        SqlSessionUtil.insertOp(statement,manager,sessionFactory);
    }
}
