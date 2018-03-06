package com.jobBridge.Dao;

import com.jobBridge.model.*;

import java.util.List;

/**
 * Created by Administrator on 2017/7/27 0027.
 */
public interface IResumeDetailDao {
    ResumeHead findResumeHeadByResumeId(Long resumeId);
    void addResumeHead(ResumeHead resumeHead);
    void deleteResumeHeadByResumeId(Long resumeId);

    List<ResumeEducation> findResumeEducationByResumeId(Long resumeId);
    void addResumeEducation(ResumeEducation resumeEducation);
    void deleteResumeEducationByResumeId(Long resumeId);

    List<ResumeWorks> findResumeWorksByResumeId(Long resumeId);
    void addResumeWorks(ResumeWorks resumeWorks);
    void deleteResumeWorksByResumeId(Long resumeId);

    List<ResumeOrganization> findResumeOrganizationByResumeId(Long resumeId);
    void addResumeOrganization(ResumeOrganization resumeOrganization);
    void deleteResumeOrganizationByResumeId(Long resumeId);

    List<ResumeClub> findResumeClubByResumeId(Long resumeId);
    void addResumeClub(ResumeClub resumeClub);
    void deleteResumeClubByResumeId(Long resumeId);

    ResumeSkill findResumeSkillByResumeId(Long resumeId);
    void addResumeSkill(ResumeSkill resumeSkill);
    void deleteResumeSkillByResumeId(Long resumeId);


}
