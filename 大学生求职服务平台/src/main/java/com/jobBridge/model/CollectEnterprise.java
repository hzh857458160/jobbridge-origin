package com.jobBridge.model;

/**
 * Created by GeniusHe on 2017/7/20.
 * 收藏公司
 */
public class CollectEnterprise {
    private Long enterpriseId;  //被收藏的公司ID
    private Long studentId;     //发起收藏请求的学生ID

    public CollectEnterprise(Long enterpriseId, Long studentId) {
        this.enterpriseId = enterpriseId;
        this.studentId = studentId;
    }

    public Long getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }
}
