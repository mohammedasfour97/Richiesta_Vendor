package com.richiestavendor.ModelClasses;

import com.richiestavendor.TinyDB;

public class IDName {

    private String id , name_ar , name_en;

    public IDName(String id, String name_ar, String name_en) {
        this.id = id;
        this.name_ar = name_ar;
        this.name_en = name_en;
    }

    public IDName(String name_ar, String name_en) {
        this.name_ar = name_ar;
        this.name_en = name_en;
    }

    public IDName() {
    }

    @Override
    public String toString() {

        return name_ar + "," + name_en;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName_ar() {
        return name_ar;
    }

    public void setName_ar(String name_ar) {
        this.name_ar = name_ar;
    }

    public String getName_en() {
        return name_en;
    }

    public void setName_en(String name_en) {
        this.name_en = name_en;
    }
}
