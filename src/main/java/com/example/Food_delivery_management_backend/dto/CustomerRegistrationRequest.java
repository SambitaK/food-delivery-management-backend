package com.example.Food_delivery_management_backend.dto;

public class CustomerRegistrationRequest {
    private String email;
    private String password;
    private String phoneNumber;
    private String deliveryAddress;
    private String alternatePhone;




    public CustomerRegistrationRequest(String email, String password, String phoneNumber,
                                       String deliveryAddress, String alternatePhone) {
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.deliveryAddress = deliveryAddress;
        this.alternatePhone = alternatePhone;
    }


    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public String getAlternatePhone() {
        return alternatePhone;
    }


    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public void setAlternatePhone(String alternatePhone) {
        this.alternatePhone = alternatePhone;
    }
}
