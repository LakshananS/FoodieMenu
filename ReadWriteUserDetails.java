package com.example.foodiemenu;

public class ReadWriteUserDetails {
    public String user_name, personal_address, phone_number;

    //using constructors for initilaize the variables
    public ReadWriteUserDetails(String uname, String number,String address) {
        this.user_name = uname;
        this.phone_number = number;
        this.personal_address = address;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getPersonal_address() {
        return personal_address;
    }

    public void setPersonal_address(String personal_address) {
        this.personal_address = personal_address;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }
}
