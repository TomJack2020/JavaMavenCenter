package com.itheima.pojo;

public class ProductForbidden {






    // id	sku	platform_code	risk_grade	risk_grade_type	create_time	create_user	update_user	update_time	is_del	reason	forbidden_source	forbidden_data_type


    @Override
    public String toString() {
        return "ProductForbidden{" +
                "id=" + id +
                ", sku='" + sku + '\'' +
                ", platform_code='" + platform_code + '\'' +
                ", risk_grade='" + risk_grade + '\'' +
                ", risk_grade_type='" + risk_grade_type + '\'' +
                ", create_time='" + create_time + '\'' +
                ", create_user='" + create_user + '\'' +
                ", update_user='" + update_user + '\'' +
                ", update_time='" + update_time + '\'' +
                ", is_del=" + is_del +
                ", reason='" + reason + '\'' +
                ", forbidden_source='" + forbidden_source + '\'' +
                ", forbidden_data_type='" + forbidden_data_type + '\'' +
                '}';
    }

    private Integer id;
    private String sku;
    private String platform_code;
    private String risk_grade;
    private String risk_grade_type;
    private String create_time; //创建时间
    private String create_user;
    private String update_user; //更新人
    private String update_time; //更新时间
    private Integer is_del;
    private String reason;
    private String forbidden_source;
    private String forbidden_data_type;

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

    public String getPlatform_code() {
        return platform_code;
    }

    public void setPlatform_code(String platform_code) {
        this.platform_code = platform_code;
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

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getForbidden_source() {
        return forbidden_source;
    }

    public void setForbidden_source(String forbidden_source) {
        this.forbidden_source = forbidden_source;
    }

    public String getForbidden_data_type() {
        return forbidden_data_type;
    }

    public void setForbidden_data_type(String forbidden_data_type) {
        this.forbidden_data_type = forbidden_data_type;
    }

}
