package com.oscar.tecmi.actividad4oscarv3.models;



public class pokemon {
    private int pkNumber;
    private String pkName;
    private String pkUrl;


    public int getPkNumber() {
        return pkNumber;
    }

    public int setPkNumber(int pkNumber) {
        String[] urlParts = pkUrl.split("/");
        return Integer.parseInt(urlParts[urlParts.length -1]);
    }

    public String getPkName() {
        return pkName;
    }

    public void setPkName(String pkName) {
        this.pkName = pkName;
    }

    public String getPkUrl() {
        return pkUrl;
    }

    public void setPkUrl(String pkUrl) {
        this.pkUrl = pkUrl;
    }
}
