package com.jobBridge.util;

import com.jobBridge.model.RecruitInfo;

import java.util.List;

/**
 * Created by SYunk on 2017/7/27.
 */
public class RecruitInfoUtil {
    public static List<RecruitInfo> orderByTime(List<RecruitInfo> recruitInfoList){
        for(int i = 0;i<recruitInfoList.size() - 1;i++){
            for(int j = 0;j<recruitInfoList.size() - i - 1;j++){
                if(recruitInfoList.get(j).getDateTime().before(recruitInfoList.get(j+1).getDateTime())){
                    RecruitInfo temp = recruitInfoList.get(j);
                    recruitInfoList.set(j,recruitInfoList.get(j + 1));
                    recruitInfoList.set(j + 1,temp);
                }
            }
        }
        return recruitInfoList;
    }
}
