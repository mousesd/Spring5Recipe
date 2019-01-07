package net.homenet.domain;

import java.io.Serializable;

public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String firstName;
    private String lastName;
    private String company;
    private String address;
    private String city;
    private String state;
    private String zip;
    private String county;
    private String url;
    private String phoneNumber;
    private String fax;

    public User() { }

    public User(String firstName, String lastName, String company, String address, String city, String state
        , String zip, String county, String url, String phoneNumber, String fax) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.company = company;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.county = county;
        this.url = url;
        this.phoneNumber = phoneNumber;
        this.fax = fax;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    @Override
    public String toString() {
        return "User{" +
            "firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", company='" + company + '\'' +
            ", address='" + address + '\'' +
            ", city='" + city + '\'' +
            ", state='" + state + '\'' +
            ", zip='" + zip + '\'' +
            ", county='" + county + '\'' +
            ", url='" + url + '\'' +
            ", phoneNumber='" + phoneNumber + '\'' +
            ", fax='" + fax + '\'' +
            '}';
    }
}
