package com.demo.liur.cacheweather.model;

/**
 * 实体类：县
 * Created by Liur on 2016/6/15.
 */
public class County extends Area{
    private int id;
    private String countyCode;
    private String countyName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountyCode() {
        return countyCode;
    }

    public void setCountyCode(String countyCode) {
        this.countyCode = countyCode;
    }

    public String getCountyName() {
        return countyName;
    }

    public void setCountyName(String countyName) {
        this.countyName = countyName;
    }
}
