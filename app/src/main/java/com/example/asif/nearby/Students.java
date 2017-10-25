package com.example.asif.nearby;

/**
 * Created by asif on 10/13/17.
 */

public class Students {
    static Students current=new Students();
    String name;
    String pass;
    String mail;
    String dept;
    String year;
    String location;
    String blood;
    String uid;

    //Modified By ASIF
    double lat,lon;

    public Students()
    {
        name=pass=mail=dept=year=location=blood=uid="Omi";
        lat=lon= 0.0;
    }
    public Students(String name, String pass, String mail, String dept, String year, String location, String blood,String uid,double lat,double lon){
        this.name = name;
        this.pass = pass;
        this.mail = mail;
        this.dept = dept;
        this.year = year;
        this.location = location;
        this.blood = blood;
        this.uid=uid;
        this.lat = lat;
        this.lon = lon;
    }
    public void allSet(Students s)
    {
        this.name = s.name;
        this.pass = s.pass;
        this.mail = s.mail;
        this.dept = s.dept;
        this.year = s.year;
        this.location = s.location;
        this.blood = s.blood;
        this.uid=s.uid;
        this.lat = s.lat;
        this.lon = s.lon;

    }
  /*  boolean isValid()
    {
        boolean flag=true;
        flag=flag && (Search.curr.name.equals("none") || this.name.contains(Search.curr.name));
        flag=flag && (Search.curr.dept.equals("none") || this.dept.equals(Search.curr.dept));
        flag=flag && (Search.curr.year.equals("none") || this.year.equals(Search.curr.year));
        flag=flag && (Search.curr.blood.equals("none") || this.blood.equals(Search.curr.blood));
        return flag;

    }*/

    public static Students getCurrent() {
        return current;
    }

    public static void setCurrent(Students current) {
        Students.current = current;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getBlood() {
        return blood;
    }

    public void setBlood(String blood) {
        this.blood = blood;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }
}