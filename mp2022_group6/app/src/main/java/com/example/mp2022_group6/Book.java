package com.example.mp2022_group6;

public class Book {

    public String date, from, destination, price, time, namebus, statusseat, seatname, email, name, age, phone;

    public Book() {
    }

    public Book(String date, String from, String destination, String price, String time, String namebus, String statusseat, String seatname, String email, String name, String age, String phone) {
        this.date = date;
        this.from = from;
        this.destination = destination;
        this.price = price;
        this.time = time;
        this.namebus = namebus;
        this.statusseat = statusseat;
        this.seatname = seatname;
        this.email = email;
        this.name = name;
        this.age = age;
        this.phone = phone;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getNamebus() {
        return namebus;
    }

    public void setNamebus(String namebus) {
        this.namebus = namebus;
    }

    public String getStatusseat() {
        return statusseat;
    }

    public void setStatusseat(String statusseat) {
        this.statusseat = statusseat;
    }

    public String getSeatname() {
        return seatname;
    }

    public void setSeatname(String seatname) {
        this.seatname = seatname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
