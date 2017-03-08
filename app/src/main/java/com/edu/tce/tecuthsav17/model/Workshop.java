package com.edu.tce.tecuthsav17.model;

/**
 * Created by RaamKumr on 2/18/2017.
 */

public class Workshop {

    private String ws_dept,ws_desc,ws_fees,ws_person,ws_title;

    public Workshop(String ws_dept, String ws_desc, String ws_fees, String ws_person, String ws_title) {
        this.ws_dept = ws_dept;
        this.ws_desc = ws_desc;
        this.ws_fees = ws_fees;
        this.ws_person = ws_person;
        this.ws_title = ws_title;
    }

    public Workshop() {
    }

    public String getWs_dept() {
        return ws_dept;
    }

    public void setWs_dept(String ws_dept) {
        this.ws_dept = ws_dept;
    }

    public String getWs_desc() {
        return ws_desc;
    }

    public void setWs_desc(String ws_desc) {
        this.ws_desc = ws_desc;
    }

    public String getWs_fees() {
        return ws_fees;
    }

    public void setWs_fees(String ws_fees) {
        this.ws_fees = ws_fees;
    }

    public String getWs_person() {
        return ws_person;
    }

    public void setWs_person(String ws_person) {
        this.ws_person = ws_person;
    }

    public String getWs_title() {
        return ws_title;
    }

    public void setWs_title(String ws_title) {
        this.ws_title = ws_title;
    }
}
