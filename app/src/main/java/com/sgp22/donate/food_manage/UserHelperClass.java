package com.sgp22.donate.food_manage;

public class UserHelperClass {
    String Name;
    String Mobile_num;
    String EmailRegister;
    String PasswordRegister;
    String Address;
    int from;
    String Uid;

    public String getUid() {
        return Uid;
    }

    public void setUid(String uid) {
        Uid = uid;
    }

    public UserHelperClass(){

    }

    public UserHelperClass(String uid,String name, String mobile_num, String emailRegister, String passwordRegister, String address) {
        Uid = uid;
        Name = name;
        Mobile_num = mobile_num;
        EmailRegister = emailRegister;
        PasswordRegister = passwordRegister;
        Address = address;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getMobile_num() {
        return Mobile_num;
    }

    public void setMobile_num(String mobile_num) {
        Mobile_num = mobile_num;
    }

    public String getEmailRegister() {
        return EmailRegister;
    }

    public void setEmailRegister(String emailRegister) {
        EmailRegister = emailRegister;
    }

    public String getPasswordRegister() {
        return PasswordRegister;
    }


    public void setPasswordRegister(String passwordRegister) {
        PasswordRegister = passwordRegister;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public int getfrom() {
        return from;
    }

    public void setfrom(int from) {
        from = from;
    }
}
