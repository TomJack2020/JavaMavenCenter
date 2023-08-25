package com.itheima.pojo;

public class ProductPicture {


    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getImage_index() {
        return image_index;
    }

    public void setImage_index(int image_index) {
        this.image_index = image_index;
    }

    @Override
    public String toString() {
        return "ProductPicture{" +
                "sku='" + sku + '\'' +
                ", image='" + image + '\'' +
                ", image_index=" + image_index +
                '}';
    }

    private String sku;
    private String image;
    private int image_index;
}
