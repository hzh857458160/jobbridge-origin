package com.jobBridge.model;

/**
 * Created by SYunk on 2017/7/20.
 */
public class Tag {
    private Integer tagId;
    private String name;

    public Tag(Integer tagId, String name) {
        this.tagId = tagId;
        this.name = name;
    }

    public Integer getTagId() {
        return tagId;
    }

    public String getName() {
        return name;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }

    public void setName(String name) {
        this.name = name;
    }
}
