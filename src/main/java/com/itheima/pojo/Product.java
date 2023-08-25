package com.itheima.pojo;

import com.sun.jmx.snmp.Timestamp;

import java.util.Date;

public class Product {

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getSpu() {
        return spu;
    }

    public void setSpu(String spu) {
        this.spu = spu;
    }



    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getTitle_cn() {
        return title_cn;
    }

    public void setTitle_cn(String title_cn) {
        this.title_cn = title_cn;
    }

    private String sku;
    private String spu;

    private String title_cn;
    private String end_time;

    public int getProduct_category_id() {
        return product_category_id;
    }

    public void setProduct_category_id(int product_category_id) {
        this.product_category_id = product_category_id;
    }

    @Override
    public String toString() {
        return "Product{" +
                "sku='" + sku + '\'' +
                ", spu='" + spu + '\'' +
                ", title_cn='" + title_cn + '\'' +
                ", product_category_id=" + product_category_id +
                ", end_time='" + end_time + '\'' +

                '}';
    }

    private int product_category_id;

}
