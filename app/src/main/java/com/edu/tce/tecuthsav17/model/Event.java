package com.edu.tce.tecuthsav17.model;

/**
 * Created by RaamKumr on 2/15/2017.
 */

public class Event {

    private String event_name,event_post,event_desc,event_outcome,event_mode,event_teamsize,event_time,event_venue,event_coord,event_phno,event_rules;

    public Event(String event_name, String event_post, String event_desc, String event_outcome, String event_mode, String event_teamsize, String event_time, String event_venue, String event_coord, String event_phno, String event_rules) {
        this.event_name = event_name;
        this.event_post = event_post;
        this.event_desc = event_desc;
        this.event_outcome = event_outcome;
        this.event_mode = event_mode;
        this.event_teamsize = event_teamsize;
        this.event_time = event_time;
        this.event_venue = event_venue;
        this.event_coord = event_coord;
        this.event_phno = event_phno;
        this.event_rules = event_rules;
    }

    public Event() {
    }

    public String getEvent_name() {
        return event_name;
    }

    public void setEvent_name(String event_name) {
        this.event_name = event_name;
    }

    public String getEvent_post() {
        return event_post;
    }

    public void setEvent_post(String event_post) {
        this.event_post = event_post;
    }

    public String getEvent_desc() {
        return event_desc;
    }

    public void setEvent_desc(String event_desc) {
        this.event_desc = event_desc;
    }

    public String getEvent_outcome() {
        return event_outcome;
    }

    public void setEvent_outcome(String event_outcome) {
        this.event_outcome = event_outcome;
    }

    public String getEvent_mode() {
        return event_mode;
    }

    public void setEvent_mode(String event_mode) {
        this.event_mode = event_mode;
    }

    public String getEvent_teamsize() {
        return event_teamsize;
    }

    public void setEvent_teamsize(String event_teamsize) {
        this.event_teamsize = event_teamsize;
    }

    public String getEvent_time() {
        return event_time;
    }

    public void setEvent_time(String event_time) {
        this.event_time = event_time;
    }

    public String getEvent_venue() {
        return event_venue;
    }

    public void setEvent_venue(String event_venue) {
        this.event_venue = event_venue;
    }

    public String getEvent_coord() {
        return event_coord;
    }

    public void setEvent_coord(String event_coord) {
        this.event_coord = event_coord;
    }

    public String getEvent_phno() {
        return event_phno;
    }

    public void setEvent_phno(String event_phno) {
        this.event_phno = event_phno;
    }

    public String getEvent_rules() {
        return event_rules;
    }

    public void setEvent_rules(String event_rules) {
        this.event_rules = event_rules;
    }
}
