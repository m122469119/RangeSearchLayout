package com.mix.rangesearchlayout;

import java.io.Serializable;

/**
 * Des:       DataInfo
 * Create by: m122469119
 * On:        2018/8/21 10:28
 * Email:     122469119@qq.com
 */
public class DataInfo implements Serializable {

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    private String Id = "";
    private String Name = "";

    public DataInfo() {

    }

    public DataInfo(String id, String name) {
        Id = id;
        Name = name;
    }

    public DataInfo(String id, String name, Boolean ischeck) {
        Id = id;
        Name = name;
    }


}
