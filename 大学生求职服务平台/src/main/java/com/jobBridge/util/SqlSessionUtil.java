package com.jobBridge.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;
import java.util.List;
import java.util.Map;

public class SqlSessionUtil{

    private static SqlSession session;

	public static SqlSessionFactory sqlSessionFactoryBuild(){
		SqlSessionFactory sessionFactory = null;
		String resource = "mybatisConf.xml";

        try {
            Reader reader = Resources.getResourceAsReader(resource);
            sessionFactory = new SqlSessionFactoryBuilder().build(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sessionFactory;
	}
	public static Object selectOp (String statement, Object obj, SqlSessionFactory sessionFactory){
		Object returnObj = null;
		try{
            session = sessionFactory.openSession();
            returnObj = session.selectOne(statement,obj);
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            session.close();
        }
        return returnObj;
	}
	public static List<Object> selectListOp(String statement, Object obj, SqlSessionFactory sessionFactory){
		List<Object> list = null;
		try{
            session = sessionFactory.openSession();
            list = session.selectList(statement,obj);
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            session.close();
        }
        return list;
	}
    public static List<Object> selectListOp(String statement, Map<String,Object> map, SqlSessionFactory sessionFactory){
        List<Object> list = null;
        try{
            session = sessionFactory.openSession();
            list = session.selectList(statement,map);
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            session.close();
        }
        return list;
    }
	public static void insertOp(String statement, Object obj, SqlSessionFactory sessionFactory){
		try{
            session = sessionFactory.openSession();
            int result = session.insert(statement,obj);
            session.commit();  //一定要记得commit
            if(result > 0){
                System.out.println("insert success");
            }else{
                System.out.println("insert error");
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            session.close();
        }
	}
	public static void deleteOp(String statement, Object obj, SqlSessionFactory sessionFactory){
		 try{
            session = sessionFactory.openSession();
            int result = session.delete(statement, obj);
            session.commit();  //一定要记得commit
            if(result > 0){
                System.out.println("delete success");
            }else{
                System.out.println("delete error");
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            session.close();
        }
	}
	public static void deleteOp(String statement, Map<String,Object> map, SqlSessionFactory sessionFactory){
		try{
            session = sessionFactory.openSession();
            int result = session.delete(statement, map);
            session.commit();  //一定要记得commit
            if(result > 0){
                System.out.println("delete success");
            }else{
                System.out.println("delete error");
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            session.close();
        }
	}
    public static void updateOp(String statement, Object object, SqlSessionFactory sessionFactory) {
        try {
            session = sessionFactory.openSession();
            int result = session.update(statement, object);
            session.commit();  //一定要记得commit
            if (result > 0) {
                System.out.println("update success");
            } else {
                System.out.println("update error");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}