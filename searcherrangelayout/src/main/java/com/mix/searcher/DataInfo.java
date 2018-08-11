package com.mix.searcher;


/**
 * 作者：鬰  on 2017/3/30 10:52
 * 邮箱：122469119@qq.com
 */
public class DataInfo {

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

    public Boolean getCheck() {
        return IsCheck;
    }

    public void setCheck(Boolean check) {
        IsCheck = check;
    }

    private String Id;
    private String Name;
    private Boolean IsCheck = false;

    public DataInfo() {

    }

    public DataInfo(String id, String name) {
        Id = id;
        Name = name;
    }

    public DataInfo(String id, String name, Boolean ischeck) {
        Id = id;
        Name = name;
        IsCheck = ischeck;
    }


}
