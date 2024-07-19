package com.itheima.pojo;



public class ProductInfoCountry {


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getCountry_code() {
        return country_code;
    }

    public void setCountry_code(String country_code) {
        this.country_code = country_code;
    }

    public String getRisk_grade() {
        return risk_grade;
    }

    public void setRisk_grade(String risk_grade) {
        this.risk_grade = risk_grade;
    }

    public String getRisk_grade_type() {
        return risk_grade_type;
    }

    public void setRisk_grade_type(String risk_grade_type) {
        this.risk_grade_type = risk_grade_type;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getCreate_user() {
        return create_user;
    }

    public void setCreate_user(String create_user) {
        this.create_user = create_user;
    }

    public String getUpdate_user() {
        return update_user;
    }

    public void setUpdate_user(String update_user) {
        this.update_user = update_user;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }

    public Integer getIs_del() {
        return is_del;
    }

    public void setIs_del(Integer is_del) {
        this.is_del = is_del;
    }

    public String getInfringement_type() {
        return infringement_type;
    }

    public void setInfringement_type(String infringement_type) {
        this.infringement_type = infringement_type;
    }

    public String getInfringement() {
        return infringement;
    }

    public void setInfringement(String infringement) {
        this.infringement = infringement;
    }

    public Integer getInfringement_source() {
        return infringement_source;
    }

    public void setInfringement_source(Integer infringement_source) {
        this.infringement_source = infringement_source;
    }

    public String getInfringement_info() {
        return infringement_info;
    }

    public void setInfringement_info(String infringement_info) {
        this.infringement_info = infringement_info;
    }

    @Override
    public String toString() {
        return "ProductInfoCountry{" +
                "id=" + id +
                ", sku='" + sku + '\'' +
                ", country_code='" + country_code + '\'' +
                ", risk_grade='" + risk_grade + '\'' +
                ", risk_grade_type='" + risk_grade_type + '\'' +
                ", create_time='" + create_time + '\'' +
                ", create_user='" + create_user + '\'' +
                ", update_user='" + update_user + '\'' +
                ", update_time='" + update_time + '\'' +
                ", is_del=" + is_del +
                ", infringement_type='" + infringement_type + '\'' +
                ", infringement='" + infringement + '\'' +
                ", infringement_source=" + infringement_source +
                ", infringement_info='" + infringement_info + '\'' +
                '}';
    }

    private Integer id;
    private String sku;
    private String country_code;
    private String risk_grade;   //风险等级
    private String risk_grade_type;   //风险等级类型
    private String create_time;
    private String create_user;
    private String update_user;
    private String update_time;
    private Integer is_del;
    private String infringement_type;   //违规类型
    private String infringement;   //违规内容
    private Integer infringement_source;   //违规来源
    private String infringement_info;   //违规信息


}
