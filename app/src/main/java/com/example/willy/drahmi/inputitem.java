package com.example.willy.drahmi;

/**
 * Created by Walid on 05/01/2018.
 */

public class inputitem {
    String id;
    String objectif;
    float ammount;
    String date;
    String time;

    public String getId() {
        return id;
    }

    public String getObjectif() {
        return objectif;
    }

    public float getAmmount() {
        return ammount;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setObjectif(String objectif) {
        this.objectif = objectif;
    }

    public void setAmmount(float ammount) {
        this.ammount = ammount;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
