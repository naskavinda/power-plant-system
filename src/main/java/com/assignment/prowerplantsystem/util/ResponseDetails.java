package com.assignment.prowerplantsystem.util;

public final class ResponseDetails {

    public static final ResponseDetails E1001 = new ResponseDetails("E1001", "Records are already in the data base.");
    public static final ResponseDetails E1002 = new ResponseDetails("E1002", "Request contain duplicate names.");


    private String code;
    private String description;

    private ResponseDetails() {
    }

    private ResponseDetails(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
