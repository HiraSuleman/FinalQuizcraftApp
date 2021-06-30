package com.example.quizcraftappfyp.models;

public class UserModel {
    String name, email, uid;
    private String Designation;
    boolean isAdmin,isTeacher;

    public UserModel(){}


    public UserModel(String name, String email, String uid, String Designation, boolean isAdmin,boolean isTeacher) {
        this.name = name;
        this.email = email;
        this.uid = uid;
        this.isAdmin = isAdmin;
        this.isTeacher=isTeacher;
        this.Designation=Designation;


    }

    public String getDesignation() {
        return Designation;
    }

    public void setDesignation(String designation) {
        Designation = designation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
    public boolean isTeacher() {
        return isTeacher;
    }

    public void setTeacher(boolean teacher) {
        isTeacher = teacher;
    }



}
