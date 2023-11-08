package com.example.btlandroid;

import java.io.Serializable;

public class dish implements Serializable {
    private int ID;
    private String namee;
    private int price;
    private byte[] image;

    public dish(int id, String namee, int price, byte[] image) {
        this.ID=id;
        this.namee = namee;
        this.price = price;
        this.image = image;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNamee() {
        return namee;
    }

    public void setNamee(String namee) {
        this.namee = namee;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public byte[] getImage() {return image;}

    public void setImage(byte[] image) {
        this.image = image;
    }
}
