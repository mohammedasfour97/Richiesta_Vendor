package com.richiestavendor.ModelClasses;

public class SoupProperty {

    private String name , string_value;
    private Object type;

    public SoupProperty(String name, String string_value, Object type) {
        this.name = name;
        this.string_value = string_value;
        this.type = type;
    }

    public SoupProperty() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getString_value() {
        return string_value;
    }

    public void setString_value(String string_value) {
        this.string_value = string_value;
    }

    public Object getType() {
        return type;
    }

    public void setType(Object type) {
        this.type = type;
    }


}
