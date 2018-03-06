package com.jobBridge.Dao;

import com.jobBridge.model.Resume;

/**
 * Created by SYunk on 2017/7/21.
 */
public interface IResumeDao {
    Long findResumeIdByStudentId(Long studentId);
    Resume findResumeByResumeId(Long resumeId); //通过resumeID找到resume实例
    void addResume(Resume resume);
}
