package com.jobBridge.Dao;

import com.jobBridge.model.RecruitInfo;
import com.jobBridge.model.RecruitInfoTag;

import java.util.List;

/**
 * Created by SYunk on 2017/7/25.
 */
public interface IRecruitInfoTagDao {
    List<RecruitInfoTag> findRecruitInfoTagByRecruitInfoId(Long recruitInfoId);
    List<RecruitInfoTag> findRecruitInfoTagByTagId(Integer tagId);
    void addRecruitInfoTag(RecruitInfoTag recruitInfoTag);
    void deleteRecruitInfoTagByRecruitInfoId(Long recruitInfoId);
}
