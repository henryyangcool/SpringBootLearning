package com.example.springbootlearning.Log;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public final class LogAspectData implements Serializable {
    private Date startTime;
    private Date endTime;
    private int spendTime;
    private String uri;
    private String url;
    private String method;
    private String methodName;
    private String ip;

    public String toString(){
        return "{" + "startTime= " + startTime + ", endTime= " + endTime + ", spendTime= "
                + spendTime + " ms, uri='" + uri + '\'' + ", url='" + url + '\'' + ", method='" + method
                + '\'' + ", methodName='" + methodName + '\'' + ", ip='" + ip + '\'' + '}';
    }
}
