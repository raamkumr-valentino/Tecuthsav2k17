package com.edu.tce.tecuthsav17.model;

/**
 * Created by RaamKumr on 2/16/2017.
 */

public class Contact {

    private String member_name,member_dept,member_phn,member_email;

    public Contact(String member_name, String member_dept, String member_phn, String member_email) {
        this.member_name = member_name;
        this.member_dept = member_dept;
        this.member_phn = member_phn;
        this.member_email = member_email;
    }

    public Contact() {
    }

    public String getMember_name() {
        return member_name;
    }

    public void setMember_name(String member_name) {
        this.member_name = member_name;
    }

    public String getMember_dept() {
        return member_dept;
    }

    public void setMember_dept(String member_dept) {
        this.member_dept = member_dept;
    }

    public String getMember_phn() {
        return member_phn;
    }

    public void setMember_phn(String member_phn) {
        this.member_phn = member_phn;
    }

    public String getMember_email() {
        return member_email;
    }

    public void setMember_email(String member_email) {
        this.member_email = member_email;
    }
}
