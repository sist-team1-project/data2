package com.sist.main;

import java.util.*;

public class CampingVO {

    private int camp_id;
    private String camp_name;
    private String camp_type;
    private String camp_facility1;
    private String camp_facility2;
    private String camp_address;
    private double camp_lat;
    private double camp_lang;

    public int getCamp_id() {
        return camp_id;
    }

    public void setCamp_id(int camp_id) {
        this.camp_id = camp_id;
    }

    public String getCamp_name() {
        return camp_name;
    }

    public void setCamp_name(String camp_name) {
        this.camp_name = camp_name;
    }

    public String getCamp_type() {
        return camp_type;
    }

    public void setCamp_type(String camp_type) {
        this.camp_type = camp_type;
    }

    public String getCamp_facility1() {
        return camp_facility1;
    }

    public void setCamp_facility1(String camp_facility1) {
        this.camp_facility1 = camp_facility1;
    }

    public String getCamp_facility2() {
        return camp_facility2;
    }

    public void setCamp_facility2(String camp_facility2) {
        this.camp_facility2 = camp_facility2;
    }

    public String getCamp_address() {
        return camp_address;
    }

    public void setCamp_address(String camp_address) {
        this.camp_address = camp_address;
    }

    public double getCamp_lat() {
        return camp_lat;
    }

    public void setCamp_lat(double camp_lat) {
        this.camp_lat = camp_lat;
    }

    public double getCamp_lang() {
        return camp_lang;
    }

    public void setCamp_lang(double camp_lang) {
        this.camp_lang = camp_lang;
    }
}