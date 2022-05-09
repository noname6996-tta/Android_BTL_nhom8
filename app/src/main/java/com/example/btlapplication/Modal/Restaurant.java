package com.example.btlapplication.Modal;

import java.io.Serializable;
import java.util.Objects;

public class Restaurant implements Serializable {
    private Integer id_res;
    private String name_res;
    private String address_res;
    private String phone_res;
    private String website_res;
    private String star_res;
    private String img_res1;
    private String img_res2;
    private String img_res3;
    private String img_res4;
    private String img_res5;
    private String latitude;
    private String longitude;
    private Boolean love;

    public Restaurant() {
    }

    public Restaurant(Integer id_res, String name_res, String address_res, String phone_res, String website_res, String star_res, String img_res1, String img_res2, String img_res3, String img_res4, String img_res5, String latitude, String longitude, Boolean love) {
        this.id_res = id_res;
        this.name_res = name_res;
        this.address_res = address_res;
        this.phone_res = phone_res;
        this.website_res = website_res;
        this.star_res = star_res;
        this.img_res1 = img_res1;
        this.img_res2 = img_res2;
        this.img_res3 = img_res3;
        this.img_res4 = img_res4;
        this.img_res5 = img_res5;
        this.latitude = latitude;
        this.longitude = longitude;
        this.love = love;
    }

    public Integer getId_res() {
        return id_res;
    }

    public void setId_res(Integer id_res) {
        this.id_res = id_res;
    }

    public String getName_res() {
        return name_res;
    }

    public void setName_res(String name_res) {
        this.name_res = name_res;
    }

    public String getAddress_res() {
        return address_res;
    }

    public void setAddress_res(String address_res) {
        this.address_res = address_res;
    }

    public String getPhone_res() {
        return phone_res;
    }

    public void setPhone_res(String phone_res) {
        this.phone_res = phone_res;
    }

    public String getWebsite_res() {
        return website_res;
    }

    public void setWebsite_res(String website_res) {
        this.website_res = website_res;
    }

    public String getStar_res() {
        return star_res;
    }

    public void setStar_res(String star_res) {
        this.star_res = star_res;
    }

    public String getImg_res1() {
        return img_res1;
    }

    public void setImg_res1(String img_res1) {
        this.img_res1 = img_res1;
    }

    public String getImg_res2() {
        return img_res2;
    }

    public void setImg_res2(String img_res2) {
        this.img_res2 = img_res2;
    }

    public String getImg_res3() {
        return img_res3;
    }

    public void setImg_res3(String img_res3) {
        this.img_res3 = img_res3;
    }

    public String getImg_res4() {
        return img_res4;
    }

    public void setImg_res4(String img_res4) {
        this.img_res4 = img_res4;
    }

    public String getImg_res5() {
        return img_res5;
    }

    public void setImg_res5(String img_res5) {
        this.img_res5 = img_res5;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public Boolean getLove() {
        return love;
    }

    public void setLove(Boolean love) {
        this.love = love;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "id_res=" + id_res +
                ", name_res='" + name_res + '\'' +
                ", address_res='" + address_res + '\'' +
                ", phone_res='" + phone_res + '\'' +
                ", website_res='" + website_res + '\'' +
                ", star_res='" + star_res + '\'' +
                ", img_res1='" + img_res1 + '\'' +
                ", img_res2='" + img_res2 + '\'' +
                ", img_res3='" + img_res3 + '\'' +
                ", img_res4='" + img_res4 + '\'' +
                ", img_res5='" + img_res5 + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Restaurant that = (Restaurant) o;
        return Objects.equals(id_res, that.id_res) && Objects.equals(name_res, that.name_res) && Objects.equals(address_res, that.address_res) && Objects.equals(phone_res, that.phone_res) && Objects.equals(website_res, that.website_res) && Objects.equals(star_res, that.star_res) && Objects.equals(img_res1, that.img_res1) && Objects.equals(img_res2, that.img_res2) && Objects.equals(img_res3, that.img_res3) && Objects.equals(img_res4, that.img_res4) && Objects.equals(img_res5, that.img_res5) && Objects.equals(latitude, that.latitude) && Objects.equals(longitude, that.longitude);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_res, name_res, address_res, phone_res, website_res, star_res, img_res1, img_res2, img_res3, img_res4, img_res5, latitude, longitude);
    }
}
