package ir.maktab.entity;

import java.util.Locale;

public class Patient {
    int id;
    String name;
    String nationalCode;

    public Patient(String name, String nationalCode) {
        this.name = name;
        this.nationalCode = nationalCode;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getNationalCode() {
        return nationalCode;
    }

    @Override
    public String toString() {
        return  "Id: " + id +
                " | Name: " + name.toUpperCase() +
                " | National code: " + nationalCode;
    }
}