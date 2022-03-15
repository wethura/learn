package com.wethura.design.prototype;

public abstract class Shape implements Cloneable{

    protected String id;
    protected String type;

    protected Shape(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public abstract void draw();

    @Override
    public Object clone() {
        Object clone = null;

        try {
            clone = super.clone();
        } catch (CloneNotSupportedException e) {
            throw new IllegalStateException(e);
        }

        return clone;
    }
}
