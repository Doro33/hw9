package ir.maktab.entity;

import java.time.LocalDate;

public class Drug {
    String name;
    int quantity;
    String producer;
    LocalDate exp;

    public LocalDate getExp() {
        return exp;
    }

    public Drug(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public void setExp(LocalDate exp) {
        this.exp = exp;
    }

    @Override
    public String toString() {
        return "Drug Name: " + name +
                " | quantity: " + quantity;
    }
}
