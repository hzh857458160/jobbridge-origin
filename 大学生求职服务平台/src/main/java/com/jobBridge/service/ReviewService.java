package com.jobBridge.service;

import com.jobBridge.Dao.IReviewDao;
import com.jobBridge.model.Review;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;
import java.util.List;
import java.util.Map;

/**
 * Created by HanrAx on 2017/7/20.
 */
public class ReviewService implements IReviewDao{
    private SqlSessionFactory sessionFactory;
    private SqlSession session;
    public ReviewService() {
        String resource = "mybatisConf.xml";
        try {
            Reader reader = Resources.getResourceAsReader(resource);
            sessionFactory = new SqlSessionFactoryBuilder().build(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Review> findReviewByStudentId(Long studentId) {
        String statement = "reviewMapper.findReviewByStudentId";
        List<Review> list = null;
        try{
            session = sessionFactory.openSession();
            list = session.selectList(statement,studentId);
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            session.close();
        }
        return list;
    }

    @Override
    public List<Review> findReviewByEnterpriseId(Long enterpriseId) {
        String statement = "reviewMapper.findReviewByEnterpriseId";
        List<Review> list = null;
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
    public void addReview(Review review) {
        String statement = "reviewMapper.addReview";
        try{
            session = sessionFactory.openSession();
            int result = session.insert(statement,review);
            session.commit();  //一定要记得commit
            if(result > 0){
                System.out.println("评论成功");
            }else{
                System.out.println("评论失败，请重试");
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            session.close();
        }
    }

    @Override
    public void deleteReviewById(Map<String,Object> map) {
        String statement = "reviewMapper.deleteReviewById";
        try{
            session = sessionFactory.openSession();
            int result = session.delete(statement, map);
            session.commit();  //一定要记得commit
            if(result > 0){
                System.out.println("删除评论成功");
            }else{
                System.out.println("删除评论失败");
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            session.close();
        }
    }
}
