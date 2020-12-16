package com.example.vocalforlocal;

public class ModelRecord  {

    String id,name,image,mobile,profe,location,sd;


    public ModelRecord(String id, String name, String image, String mobile, String profe, String sd, String location) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.mobile = mobile;
        this.profe = profe;
        this.location = location;
        this.sd = sd;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getProfe() {
        return profe;
    }

    public void setProfe(String profe) {
        this.profe = profe;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getSd() {
        return sd;
    }

    public void setSd(String sd) {
        this.sd = sd;
    }
}
