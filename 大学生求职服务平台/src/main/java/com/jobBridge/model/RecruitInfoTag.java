package com.jobBridge.model;

/**
 * Created by SYunk on 2017/7/25.
 */
public class RecruitInfoTag {
    private Long recruitInfoId;
    private Integer tagId;

    public RecruitInfoTag(Long recruitInfoId, Integer tagId) {
        this.recruitInfoId = recruitInfoId;
        this.tagId = tagId;
    }

    public Long getRecruitInfoId() {
        return recruitInfoId;
    }
    public Integer getTagId() {
        return tagId;
    }

    public void setRecruitInfoId(Long recruitInfoId) {
        this.recruitInfoId = recruitInfoId;
    }
    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }
}
