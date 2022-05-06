package com.example.btlapplication.Modal;

public class City {
    private Integer idCity;
    private String nameCity;
    private String imageCity;

    public City() {
    }

    public City(Integer idCity, String nameCity, String imageCity) {
        this.idCity = idCity;
        this.nameCity = nameCity;
        this.imageCity = imageCity;
    }

    public Integer getIdCity() {
        return idCity;
    }

    public void setIdCity(Integer idCity) {
        this.idCity = idCity;
    }

    public String getNameCity() {
        return nameCity;
    }

    public void setNameCity(String nameCity) {
        this.nameCity = nameCity;
    }

    public String getImageCity() {
        return imageCity;
    }

    public void setImageCity(String imageCity) {
        this.imageCity = imageCity;
    }
}
