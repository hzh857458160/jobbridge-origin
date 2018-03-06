package com.jobBridge.model;

/**
 * Created by GeniusHe on 2017/7/20.
 * 收藏工作大标签类
 */
public class CollectTag {
    private Long studentId;     //发起收藏请求的学生ID
    private Integer tagId;      //被收藏的标签ID

    public CollectTag(Long studentId, Integer tagId) {
        this.studentId = studentId;
        this.tagId = tagId;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }
}
