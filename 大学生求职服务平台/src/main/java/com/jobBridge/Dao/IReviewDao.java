package com.jobBridge.Dao;

import com.jobBridge.model.Review;

import java.util.List;
import java.util.Map;

/**
 * Created by HanrAx on 2017/7/20.
 */
public interface IReviewDao {
    List<Review> findReviewByStudentId(Long studentId);           //学生可以查询自己发过的评论
    List<Review> findReviewByEnterpriseId(Long enterpriseId);     //公司可以查询收到的评论
    void addReview(Review review);                                //学生新增评论
    void deleteReviewById(Map<String, Object> map);                //通过传入学生ID和公司ID，删除一个唯一确定的评论
}
