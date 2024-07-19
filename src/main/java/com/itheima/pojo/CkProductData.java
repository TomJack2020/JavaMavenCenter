package com.itheima.pojo;

public class CkProductData {


    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getDev_type_p() {
        return dev_type_p;
    }

    public void setDev_type_p(String dev_type_p) {
        this.dev_type_p = dev_type_p;
    }

    public String getProduct_sx() {
        return product_sx;
    }

    public void setProduct_sx(String product_sx) {
        this.product_sx = product_sx;
    }

    public String getProduct_status() {
        return product_status;
    }

    public void setProduct_status(String product_status) {
        this.product_status = product_status;
    }

    public String getLine1() {
        return line1;
    }

    public void setLine1(String line1) {
        this.line1 = line1;
    }

    private String sku;
    private String dev_type_p;
    private String product_sx;
    private String product_status;

    public String getLine2() {
        return line2;
    }

    public void setLine2(String line2) {
        this.line2 = line2;
    }

    public String getLine4() {
        return line4;
    }

    public void setLine4(String line4) {
        this.line4 = line4;
    }

    public String getLine3() {
        return line3;
    }

    public void setLine3(String line3) {
        this.line3 = line3;
    }

    private String line1;
    private String line2;
    private String line3;
    private String line4;



    @Override
    public String toString() {
        return "CkProductData{" +
                "sku='" + sku + '\'' +
                ", dev_type_p='" + dev_type_p + '\'' +
                ", product_sx='" + product_sx + '\'' +
                ", product_status='" + product_status + '\'' +
                ", line1='" + line1 + '\'' +
                ", line2='" + line2 + '\'' +
                ", line3='" + line3 + '\'' +
                ", line4='" + line4 + '\'' +
                '}';
    }





}
