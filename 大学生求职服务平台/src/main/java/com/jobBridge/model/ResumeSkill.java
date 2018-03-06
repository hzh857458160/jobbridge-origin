package com.jobBridge.model;

/**
 * Created by SYunk on 2017/7/21.
 */
public class ResumeSkill {
    private Long resumeSkillId;
    private Long resumeId;
    private String language;
    private String computer;
    private String hobby;

    public ResumeSkill(Long resumeSkillId, Long resumeId, String language, String computer, String hobby) {
        this.resumeSkillId = resumeSkillId;
        this.resumeId = resumeId;
        this.language = language;
        this.computer = computer;
        this.hobby = hobby;
    }

    public ResumeSkill() {
    }

    public Long getResumeSkillId() {
        return resumeSkillId;
    }
    public Long getResumeId() {
        return resumeId;
    }
    public String getLanguage() {
        return language;
    }
    public String getComputer() {
        return computer;
    }
    public String getHobby() {
        return hobby;
    }
    public void setResumeSkillId(Long resumeSkillId) {
        this.resumeSkillId = resumeSkillId;
    }
    public void setResumeId(Long resumeId) {
        this.resumeId = resumeId;
    }
    public void setLanguage(String language) {
        this.language = language;
    }
    public void setComputer(String computer) {
        this.computer = computer;
    }
    public void setHobby(String hobby) {
        this.hobby = hobby;
    }
}
