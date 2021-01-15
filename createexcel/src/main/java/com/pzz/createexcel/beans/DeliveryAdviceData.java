package com.pzz.createexcel.beans;

/**
 * @program: easyexcel
 * @description:
 * @author: pzz
 * @create: 2021-01-14 16:51
 **/
public class DeliveryAdviceData {
    private String date;
    private String no;
    private String grossweight;
    private String tareweight;

    public DeliveryAdviceData(String date, String no, String grossweight, String tareweight) {
        this.date = date;
        this.no = no;
        this.grossweight = grossweight;
        this.tareweight = tareweight;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getGrossweight() {
        return grossweight;
    }

    public void setGrossweight(String grossweight) {
        this.grossweight = grossweight;
    }

    public String getTareweight() {
        return tareweight;
    }

    public void setTareweight(String tareweight) {
        this.tareweight = tareweight;
    }

    @Override
    public String toString() {
        return "{" + date + ' ' + no + ' ' + grossweight + ' ' + tareweight + ' ' + '}';
    }
}
